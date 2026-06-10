package com.pysarivka.WeekEnds.mapper;

import com.pysarivka.WeekEnds.domain.Operation;
import com.pysarivka.WeekEnds.domain.User;
import com.pysarivka.WeekEnds.domain.OperationImage;
import com.pysarivka.WeekEnds.dto.AuthorDTO;
import com.pysarivka.WeekEnds.dto.OperationDTO;
import com.pysarivka.WeekEnds.dto.OperationImageDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class OperationMapper {

    /**
     * Конвертирует сущность Operation в OperationDTO со всеми вложенными связями.
     */
    public static OperationDTO toDTO(Operation operation) {
        if (operation == null) {
            return null;
        }

        // Конвертируем автора (пользователя)
        AuthorDTO authorDTO = toAuthorDTO(operation.getAuthor());

        // Безопасно конвертируем список изображений
        List<OperationImageDTO> imageDTOs = new ArrayList<>();
        if (operation.getImages() != null) {
            imageDTOs = operation.getImages().stream()
                    .map(OperationMapper::toImageDTO)
                    .collect(Collectors.toList());
        }

        return new OperationDTO(
                operation.getId(),
                operation.getTitle(),
                operation.getDescription(),
                operation.getToolsAndMaterials(),
                operation.getLocation() != null ? operation.getLocation().getId() : null,
                authorDTO,
                imageDTOs
        );
    }

    /**
     * Конвертирует сущность User в безопасный AuthorDTO.
     */
    public static AuthorDTO toAuthorDTO(User user) {
        if (user == null) {
            return null;
        }
        return new AuthorDTO(
                user.getId(),
                user.getFirstname(),
                user.getLastname(),
                user.getIcon()
        );
    }

    /**
     * Конвертирует сущность OperationImage в OperationImageDTO.
     */
    public static OperationImageDTO toImageDTO(OperationImage image) {
        if (image == null) {
            return null;
        }
        // Предполагается, что в вашей сущности OperationImage поле называется imagePath или аналогично.
        // Если в сущности метод называется по-другому (например, getPath() или getUrl()), замените его ниже.
        return new OperationImageDTO(
                image.getId(),
                image.getImagePath() 
        );
    }
}
