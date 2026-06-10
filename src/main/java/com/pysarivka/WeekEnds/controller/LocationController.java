package com.pysarivka.WeekEnds.controller;

import java.util.List;
import java.util.Optional;
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
import com.pysarivka.WeekEnds.domain.Location;
import com.pysarivka.WeekEnds.dto.LocationDTO;
import com.pysarivka.WeekEnds.service.DayService;
import com.pysarivka.WeekEnds.service.LocationService;

@RestController
@RequestMapping("/locations") // Чиста адреса
public class LocationController {

    private final Logger logger = LoggerFactory.getLogger(LocationController.class);

    @Autowired
    private LocationService locationService;

    @Autowired
    private DayService dayService;

    // 1. Отримати список локацій, що належать конкретному дню
    @GetMapping("/by-day/{dayId}")
    public ResponseEntity<List<LocationDTO>> getLocationsByDay(@PathVariable Long dayId) {
        logger.info("REST request to get locations for day id: {}", dayId);
        List<LocationDTO> dtos = locationService.findLocationsByDayId(dayId).stream()
                .map(loc -> new LocationDTO(loc.getId(), loc.getName(), loc.getDay().getId()))
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    // 2. Додати нову локацію до конкретного дня
    @PostMapping
    public ResponseEntity<LocationDTO> createLocation(@RequestBody LocationDTO locationDTO) {
        logger.info("REST request to save/update Location: {}", locationDTO);
        Optional<Day> dayOptional = dayService.findById(locationDTO.getDayId());
        if (dayOptional.isEmpty()) return ResponseEntity.badRequest().build();

        Location location;
        if (locationDTO.getId() != null) {
            location = locationService.findById(locationDTO.getId()).orElse(new Location());
        } else {
            location = new Location();
        }
        location.setName(locationDTO.getName());
        location.setDay(dayOptional.get());

        Location savedLocation = locationService.saveLocation(location);
        LocationDTO result = new LocationDTO(savedLocation.getId(), savedLocation.getName(), savedLocation.getDay().getId());
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    // 3. Видалити локацію (автоматично видалить вкладені операції та малюнки)
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLocation(@PathVariable Long id) {
        logger.info("REST request to delete Location by id: {}", id);
        locationService.deleteLocationById(id);
        return ResponseEntity.noContent().build();
    }
}
