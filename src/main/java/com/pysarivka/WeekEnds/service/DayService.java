package com.pysarivka.WeekEnds.service;

import java.util.List;
import java.util.Optional;

import com.pysarivka.WeekEnds.domain.Day;

public interface DayService {
	
	Optional<Day>findById(Long id);
	
	Day saveDay(Day day);
	
	Day updateDay(Day day);
	
	void deleteDay(Day day);
	
	void deleteDayById(Long id);
	
	List<Day> findAllDays();

}
