package com.pysarivka.WeekEnds.service;

import java.util.List;
import java.util.Optional;

import com.pysarivka.WeekEnds.domain.User;



public interface UserService {

	Optional<User> findByEmail(String email);

	Optional<User> findUserById(Long id);

	User saveUser(User user);

	User updateUser(User user);

	void deleteUser(User user);

	void deleteUserById(Long id);
	
	void deleteUserByEmail(String email);

	List<User> findAllUsers();

}
