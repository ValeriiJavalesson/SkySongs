package com.pysarivka.Skysongs.service;

import java.util.List;
import java.util.Optional;

import com.pysarivka.Skysongs.domain.Song;

public interface SongService {
	
	Optional<Song>findById(Long id);
	
	Song saveSong(Song song);
	
	Song updateSong(Song song);
	
	void deleteSong(Song song);
	
	void deleteSongById(Long id);
	
	List<Song> findAllSongs();

}
