package com.pysarivka.Skysongs.controller;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.pysarivka.Skysongs.domain.Song;
import com.pysarivka.Skysongs.domain.User;
import com.pysarivka.Skysongs.service.impl.SongServiceImpl;
import com.pysarivka.Skysongs.service.impl.UserServiceImpl;

@RestController
public class UserController {

	@Autowired
	private SongServiceImpl songServiceImpl;

	@Autowired
	private UserServiceImpl userServiceImpl;

	@RequestMapping("/")
	public ModelAndView init() {
		return allsongs();
	}

	@RequestMapping("/allsongs")
	public ModelAndView allsongs() {
		ModelAndView model = new ModelAndView("allsongs");
		List<Song> allSongs = songServiceImpl.findAllSongs();
//		List<Song> sortedSongs = allSongs.stream().sorted(Comparator.comparing(Song::getNumber))
//				.collect(Collectors.toList());
		List<Song> sortedSongs = allSongs.stream()
				.sorted(Comparator.comparingDouble(obj -> Double.parseDouble(obj.getNumber())))
				.collect(Collectors.toList());
		model.addObject("songs", sortedSongs);

		return model;
	}

	@RequestMapping("/login")
	public ModelAndView login() {
		ModelAndView model = new ModelAndView("login");
		return model;
	}

	@GetMapping("/logout")
	public String logout() {
		return "/";
	}

	@RequestMapping("/registration")
//	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ModelAndView registration() {
		ModelAndView model = new ModelAndView("registration");
		return model;
	}

//	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@PostMapping("/registration")
	public String saveUser(@ModelAttribute("userForm") User userForm, BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
			return "registration";
		}
		Optional<User> optionalUser = userServiceImpl.findByEmail(userForm.getEmail());
		if (optionalUser.isPresent()) {
			return "registration?message=ispresent";
		}
		User user = userServiceImpl.saveUser(userForm);

		userServiceImpl.updateUser(user);
		return "login?registered=succesfully";
	}

	@GetMapping("/api/getcurrentuser")
	public String getUser() {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (principal instanceof UserDetails) {
			String username = ((UserDetails) principal).getUsername();
			return username;
		}
		return "";
	}

	@GetMapping("/isUserPresent")
	public Boolean isUserPresent(@RequestParam String email) {
		Optional<User> optionalUser = userServiceImpl.findByEmail(email);
		return optionalUser.isPresent();
	}
}
