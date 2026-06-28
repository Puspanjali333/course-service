package com.idream.course;

import jakarta.persistence.*;

@Entity
public class Course {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long id;
	public String name;
	@Column(length = 1000)
	public String description;
	public String duration;

	public Course() {
	}

	public Course(Long id, String name, String description, String duration) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.duration = duration;
	}
}