package com.pysarivka.WeekEnds.service;

import java.util.List;
import java.util.Optional;

import com.pysarivka.WeekEnds.domain.Location;

public interface LocationService {
	Optional<Location> findById(Long id);

    Location saveLocation(Location location);

    Location updateLocation(Location location);

    void deleteLocation(Location location);

    void deleteLocationById(Long id);

    List<Location> findAllLocations();

    List<Location> findLocationsByDayId(Long dayId);
}
