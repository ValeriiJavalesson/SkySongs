package com.pysarivka.WeekEnds.service.impl;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pysarivka.WeekEnds.dao.LocationRepository;
import com.pysarivka.WeekEnds.domain.Location;
import com.pysarivka.WeekEnds.service.LocationService;

@Service
@Transactional
public class LocationServiceImpl implements LocationService {

    private final Logger logger = LoggerFactory.getLogger(LocationServiceImpl.class);

    @Autowired
    private LocationRepository locationRepository;

    @Override
    @Transactional(readOnly = true)
    public Optional<Location> findById(Long id) {
        logger.info("Get location by id: " + id);
        return locationRepository.findById(id);
    }

    @Override
    public Location saveLocation(Location location) {
        logger.info("Create new location: " + location);
        return locationRepository.save(location);
    }

    @Override
    public Location updateLocation(Location location) {
        logger.info("Update location: " + location);
        return locationRepository.save(location);
    }

    @Override
    public void deleteLocation(Location location) {
        logger.info("Delete location: " + location);
        locationRepository.delete(location);
    }

    @Override
    public void deleteLocationById(Long id) {
        logger.info("Delete location by id: " + id);
        locationRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Location> findAllLocations() {
        logger.info("Get all locations");
        return locationRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Location> findLocationsByDayId(Long dayId) {
        logger.info("Get locations for day id: " + dayId);
        return locationRepository.findByDayId(dayId);
    }
}
