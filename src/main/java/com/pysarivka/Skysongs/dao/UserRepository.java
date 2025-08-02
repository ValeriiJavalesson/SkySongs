package com.pysarivka.Skysongs.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pysarivka.Skysongs.domain.User;


public interface UserRepository extends JpaRepository<User, Long> {
	Optional<User> findByEmail(String email);

	void deleteByEmail(String email);
}
