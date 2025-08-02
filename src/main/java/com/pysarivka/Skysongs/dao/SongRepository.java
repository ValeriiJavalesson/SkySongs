package com.pysarivka.Skysongs.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pysarivka.Skysongs.domain.Song;

public interface SongRepository extends JpaRepository<Song, Long>{

}
