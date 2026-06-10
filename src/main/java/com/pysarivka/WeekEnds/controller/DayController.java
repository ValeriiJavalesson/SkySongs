package com.pysarivka.WeekEnds.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pysarivka.WeekEnds.domain.Day;
import com.pysarivka.WeekEnds.dto.DayDTO;
import com.pysarivka.WeekEnds.service.DayService;

@RestController
@RequestMapping("/days")
public class DayController {

    private final Logger logger = LoggerFactory.getLogger(DayController.class);

    @Autowired
    private DayService dayService;

    // 1. Отримати всі дні для головного екрана Android
    @GetMapping
    public ResponseEntity<List<DayDTO>> getAllDays() {
        logger.info("REST request to get all days");
        List<DayDTO> dtos = dayService.findAllDays().stream()
                .map(day -> new DayDTO(day.getId(), day.getTitle()))
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    // 2. Додати новий день з Android-додатка
    @PostMapping
    public ResponseEntity<DayDTO> createDay(@RequestBody DayDTO dayDTO) {
        logger.info("REST request to save/update Day: {}", dayDTO);
        Day day;
        // Якщо ID прийшов з Android — це редагування
        if (dayDTO.getId() != null) {
            day = dayService.findById(dayDTO.getId()).orElse(new Day());
        } else {
            day = new Day();
            day.setNumber(String.valueOf(dayService.findAllDays().size() + 1));
        }
        day.setTitle(dayDTO.getDateName());
        
        Day savedDay = dayService.saveDay(day);
        DayDTO result = new DayDTO(savedDay.getId(), savedDay.getTitle());
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    // 3. Видалити день за його ID (видалить також і всі локації всередині завдяки CascadeType.ALL)
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDay(@PathVariable Long id) {
        logger.info("REST request to delete Day by id: {}", id);
        dayService.deleteDayById(id);
        return ResponseEntity.noContent().build(); // Повертає статус 204 No Content, що ідеально для мобілок
    }
}
