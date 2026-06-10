package com.pysarivka.WeekEnds.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.pysarivka.WeekEnds.domain.Location;
import com.pysarivka.WeekEnds.domain.Operation;
import com.pysarivka.WeekEnds.domain.OperationImage;
import com.pysarivka.WeekEnds.domain.User;
import com.pysarivka.WeekEnds.dto.OperationDTO;
import com.pysarivka.WeekEnds.dto.OperationImageDTO;
import com.pysarivka.WeekEnds.mapper.OperationMapper;
import com.pysarivka.WeekEnds.service.LocationService;
import com.pysarivka.WeekEnds.service.OperationImageService;
import com.pysarivka.WeekEnds.service.OperationService;
import com.pysarivka.WeekEnds.service.impl.UserServiceImpl;

@RestController
@RequestMapping("/operations")
public class OperationController {

	private final Logger logger = LoggerFactory.getLogger(OperationController.class);

	// Папка на сервері, куди фізично зберігатимуться малюнки з Android
	private final String UPLOAD_DIR = System.getProperty("user.dir") + "/uploads/";

	@Autowired
	private OperationService operationService;

	@Autowired
	private LocationService locationService;

	@Autowired
	private OperationImageService imageService;
	@Autowired
    private UserServiceImpl userServiceImpl;

    // 1. Отримати всі операції для конкретної локації
    @GetMapping("/by-location/{locationId}")
    public ResponseEntity<List<OperationDTO>> getOperationsByLocation(@PathVariable Long locationId) {
        logger.info("REST request to get operations for location id: {}", locationId);

        // Отримуємо сутності з сервісу
        List<Operation> operations = operationService.findOperationsByLocationId(locationId);

        // Перетворюємо сутності в DTO за допомогою нашого мапера (він автоматично підтягне автора та картинки)
        List<OperationDTO> dtos = operations.stream()
                .map(OperationMapper::toDTO)
                .collect(Collectors.toList());

        return ResponseEntity.ok(dtos);
    }


    // 2. Додати або оновити операцію
    @PostMapping
    @Transactional
    public ResponseEntity<OperationDTO> createOperation(
            @RequestBody OperationDTO operationDTO) { // Прибираємо нестабільний параметр з аргументів

        // 1. Надійно дістаємо користувача з контексту безпеки Spring
        org.springframework.security.core.Authentication authentication = 
                org.springframework.security.core.context.SecurityContextHolder.getContext().getAuthentication();
                
        if (authentication == null || !authentication.isAuthenticated() || 
            authentication instanceof org.springframework.security.authentication.AnonymousAuthenticationToken) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        // Дістаємо email (username), який туди записав ваш JwtAuthFilter
        String currentUsername = authentication.getName(); 
        logger.info("REST request by user {} to save/update Operation: {}", currentUsername, operationDTO);

        // Знаходимо реальний об'єкт користувача в базі даних
        Optional<User> userOptional = userServiceImpl.findByEmail(currentUsername);
        if (userOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        User currentAuthenticatedUser = userOptional.get();

        // 2. Ваша наявна логіка перевірки локації
        Optional<Location> locationOptional = locationService.findById(operationDTO.getLocationId());
        if (locationOptional.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        Operation operation;
        
        // 3. Ваша наявна логіка редагування / створення
        if (operationDTO.getId() != null) {
            Optional<Operation> existingOperationOpt = operationService.findById(operationDTO.getId());
            if (existingOperationOpt.isEmpty()) {
                return ResponseEntity.notFound().build();
            }
            
            operation = existingOperationOpt.get();
            
            if (!operation.getAuthor().getId().equals(currentAuthenticatedUser.getId())) {
                return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
            }
        } else {
            operation = new Operation();
            operation.setAuthor(currentAuthenticatedUser); 
        }

        operation.setTitle(operationDTO.getTitle());
        operation.setDescription(operationDTO.getDescription());
        operation.setToolsAndMaterials(operationDTO.getToolsAndMaterials());
        operation.setLocation(locationOptional.get());

        Operation savedOperation = operationService.saveOperation(operation);
        OperationDTO result = OperationMapper.toDTO(savedOperation);
                
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }



	// 3. Завантажити малюнок і прив'язати його до існуючої операції
	// Android надсилатиме файл за допомогою Multipart-запиту (наприклад, через
	// Retrofit або OkHttp)
	@PostMapping("/{id}/upload-image")
	public ResponseEntity<OperationImageDTO> uploadImageToOperation(@PathVariable Long id,
			@RequestParam("file") MultipartFile file) {
		logger.info("REST request to upload image for operation id: {}", id);

		Optional<Operation> operationOptional = operationService.findById(id);
		if (operationOptional.isEmpty() || file.isEmpty()) {
			return ResponseEntity.badRequest().build();
		}

		try {
			// Створюємо папку, якщо її ще немає
			File uploadDir = new File(UPLOAD_DIR);
			if (!uploadDir.exists()) {
				uploadDir.mkdirs();
			}

			// Генеруємо унікальну назву файлу, щоб уникнути однакових імен (наприклад,
			// image.jpg)
			String uniqueFileName = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();
			File destinationFile = new File(UPLOAD_DIR + uniqueFileName);

			// Зберігаємо файл на диск сервера
			file.transferTo(destinationFile);

			// Записуємо шлях/URL файлу в базу даних MySQL
			String relativePath = "/uploads/" + uniqueFileName;
			OperationImage imageEntity = new OperationImage(relativePath, operationOptional.get());
			OperationImage savedImage = imageService.saveOperationImage(imageEntity);

			return ResponseEntity.status(HttpStatus.CREATED)
					.body(new OperationImageDTO(savedImage.getId(), savedImage.getImagePath()));

		} catch (IOException e) {
			logger.error("Error saving uploaded image", e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	// 4. Видалити операцію (видалить також усі її малюнки з бази завдяки каскаду)
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteOperation(@PathVariable Long id) {
		logger.info("REST request to delete Operation by id: {}", id);
		operationService.deleteOperationById(id);
		return ResponseEntity.noContent().build();
	}

	// Додайте цей метод в кінець вашого OperationController.java, якщо треба
	// видаляти окремі фото
	@DeleteMapping("/images/{imageId}")
	public ResponseEntity<Void> deleteImage(@PathVariable Long imageId) {
		logger.info("REST request to delete operation image by id: {}", imageId);
		imageService.deleteOperationImageById(imageId);
		return ResponseEntity.noContent().build();
	}

}
