package com.pysarivka.Skysongs.controller;

import java.util.List;
import java.util.Comparator;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.pysarivka.Skysongs.domain.Song;
import com.pysarivka.Skysongs.service.impl.SongServiceImpl;

@RestController
public class SongController {
	@Autowired
	private SongServiceImpl songServiceImpl;

	@RequestMapping("/addsong")
	@PreAuthorize("hasAuthority('ROLE_USER')")
	public ModelAndView addsong(@RequestParam("id") Long id) {
		ModelAndView model = new ModelAndView("addsong");
		List<Song> allSongs = songServiceImpl.findAllSongs();
		
		Optional<Song> optionalMaxValueSong = allSongs.stream().max(Comparator.comparing(Song::getNumber));
		if (optionalMaxValueSong.isPresent()) {
			model.addObject("maxNumberSong", optionalMaxValueSong.get());
		}

		if (id == null) {
			return model;
		}
		Song song = null;
		Optional<Song> optionalSong = songServiceImpl.findById(id);
		if (optionalSong.isPresent()) {
			song = optionalSong.get();
			model.addObject("song", song);
			return model;
		}
		return model;
	}

	@RequestMapping("/savesong")
	@PreAuthorize("hasAuthority('ROLE_USER')")
	public String saveSong(@RequestBody Song song) {
		Song newSong = new Song();
		if (song.getId() != null) {
			Optional<Song> optionalSong = songServiceImpl.findById(song.getId());
			if (optionalSong.isPresent()) {
				newSong = optionalSong.get();
			}
		}
		List<Song> allSongs = songServiceImpl.findAllSongs();
		Optional<Song> first = allSongs.stream().filter(s-> s.getId()>song.getId()).findFirst();
		Long lastId = 0L;
		if(first.isPresent()) {
			lastId = first.get().getId();
		}
		newSong.setNumber(song.getNumber());
		newSong.setText(song.getText());
		newSong.setTitle(song.getTitle());

		if (song.getId() != null) {
			songServiceImpl.updateSong(newSong);
		} else {
			songServiceImpl.saveSong(newSong);
		}
		return "addsong?id="+lastId;
	}

	@RequestMapping("/song")
	public ModelAndView getSong(@RequestParam("id") Long id) {
		ModelAndView model = new ModelAndView("song");
		Optional<Song> optionalSong = songServiceImpl.findById(id);
		Song song = null;
		if (optionalSong.isPresent()) {
			song = optionalSong.get();
			model.addObject("song", song);
		}
		return model;
	}
	
	@RequestMapping(value = "/delete_song", method = RequestMethod.DELETE)
	@PreAuthorize("hasAuthority('ROLE_USER')")
	public String delete_song(@RequestParam Long id) {
		songServiceImpl.deleteSongById((long) id);
		return "Видалено!";
	}

}
