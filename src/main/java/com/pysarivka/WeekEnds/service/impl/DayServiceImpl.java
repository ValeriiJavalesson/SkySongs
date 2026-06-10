package com.pysarivka.WeekEnds.service.impl;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pysarivka.WeekEnds.dao.DayRepository;
import com.pysarivka.WeekEnds.domain.Day;
import com.pysarivka.WeekEnds.service.DayService;

@Service
public class DayServiceImpl implements DayService{
	private Logger logger = LoggerFactory.getLogger(DayServiceImpl.class);
	
	@Autowired
	private DayRepository repository;

	@Override
	public Optional<Day> findById(Long id) {
		logger.info("Get day by id : " + id);
		return repository.findById(id);
	}

	@Override
	public Day saveDay(Day day) {
		logger.info("Create new day : " + day);
		return repository.save(day);
	}

	@Override
	public Day updateDay(Day day) {
		logger.info("Update day : " + day);
		return repository.save(day);
	}

	@Override
	public void deleteDay(Day day) {
		logger.info("Delete day : " + day);
		repository.delete(day);
		
	}

	@Override
	public void deleteDayById(Long id) {
		logger.info("Delete day by id: " + id);
		repository.deleteById(id);
		
	}

	@Override
	public List<Day> findAllDays() {
		logger.info("Get all days");
		return repository.findAll();
	}

}
