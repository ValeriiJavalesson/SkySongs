package com.pysarivka.Skysongs.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.pysarivka.Skysongs.domain.Song;
import com.pysarivka.Skysongs.service.impl.SongServiceImpl;

@RestController
public class SongController {
	@Autowired
	private SongServiceImpl songServiceImpl;
	
	@RequestMapping("/addsong")
	public ModelAndView addsong() {
		ModelAndView model = new ModelAndView("addsong");
		return model;
	}
	
	@RequestMapping("/savesong")
	public String saveSong(@RequestBody Song song) {
		Song newSong = new Song();
		if(song.getId() != null) {
			Optional<Song> optionalSong = songServiceImpl.findById(song.getId());
			if(optionalSong.isPresent()) {
				newSong = optionalSong.get();
			}
		}
		newSong.setNumber(song.getNumber());
		newSong.setText(song.getText());
		newSong.setTitle(song.getTitle());
		
		if(song.getId() != null) {
			songServiceImpl.updateSong(newSong);
		} else {
			songServiceImpl.saveSong(newSong);
		}		
		return "addsong";
	}
	
	
}
