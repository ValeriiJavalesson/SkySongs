package com.pysarivka.WeekEnds.dao;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.pysarivka.WeekEnds.domain.Location;

@Repository
public interface LocationRepository extends JpaRepository<Location, Long> {
    
    // Спеціальний метод: знайде всі локації для конкретного дня за його id
    List<Location> findByDayId(Long dayId);
}
