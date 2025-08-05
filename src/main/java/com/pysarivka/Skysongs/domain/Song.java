package com.pysarivka.Skysongs.domain;

import java.io.Serial;
import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table
@Data
public class Song implements Serializable {

	@Serial
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@Column(unique = true)
	private String number;
	@Column
	private String title;
	@Column(length = 5000)
	private String text;
	
	@Override
	public String toString() {
		return "Song [id=" + id + ", number=" + number + ", title=" + title + "]";
	}
	
	

}
