package com.pysarivka.WeekEnds.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.pysarivka.WeekEnds.domain.Operation;

@Repository
public interface OperationRepository extends JpaRepository<Operation, Long> {
    
    // Додано LEFT JOIN FETCH o.author для завантаження автора одним запитом разом із картинками
    @Query("SELECT DISTINCT o FROM Operation o " +
           "LEFT JOIN FETCH o.images " +
           "LEFT JOIN FETCH o.author " +
           "WHERE o.location.id = :locationId")
    List<Operation> findByLocationId(@Param("locationId") Long locationId);
	
}
