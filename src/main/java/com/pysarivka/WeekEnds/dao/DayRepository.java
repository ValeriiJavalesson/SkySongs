package com.pysarivka.WeekEnds.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pysarivka.WeekEnds.domain.Day;

public interface DayRepository extends JpaRepository<Day, Long>{

}
