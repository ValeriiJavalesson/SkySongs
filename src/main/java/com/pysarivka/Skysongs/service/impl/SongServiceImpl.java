package com.pysarivka.Skysongs.service.impl;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pysarivka.Skysongs.dao.SongRepository;
import com.pysarivka.Skysongs.domain.Song;
import com.pysarivka.Skysongs.service.SongService;

@Service
public class SongServiceImpl implements SongService{
	private Logger logger = LoggerFactory.getLogger(SongServiceImpl.class);
	
	@Autowired
	private SongRepository repository;

	@Override
	public Optional<Song> findById(Long id) {
		logger.info("Get song by id : " + id);
		return repository.findById(id);
	}

	@Override
	public Song saveSong(Song song) {
		logger.info("Create new song : " + song);
		return repository.save(song);
	}

	@Override
	public Song updateSong(Song song) {
		logger.info("Update song : " + song);
		return repository.save(song);
	}

	@Override
	public void deleteSong(Song song) {
		logger.info("Delete song : " + song);
		repository.delete(song);
		
	}

	@Override
	public void deleteSongById(Long id) {
		logger.info("Delete song by id: " + id);
		repository.deleteById(id);
		
	}

	@Override
	public List<Song> findAllSongs() {
		logger.info("Get all songs");
		return repository.findAll();
	}

}
