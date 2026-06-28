package com.idream.course;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import java.util.*;

@RestController
@RequestMapping("/api/courses")
@CrossOrigin(origins = "*")
public class CourseController {

	private final CourseRepository repo;
	
	private final RestTemplate rest;
	
	@Value("${student.service.url}")
	String studentUrl;

	CourseController(CourseRepository repo, RestTemplate rest) {
		
		this.repo = repo;
		this.rest = rest;
	}

	@GetMapping
	public List<Course> all() {
		return repo.findAll();
	}

	@GetMapping("/{id}")
	public Course one(@PathVariable Long id) {
		return repo.findById(id).orElseThrow();
	}

	@PostMapping
	public Course create(@RequestBody Course c) {
		return repo.save(c);
	}

	@GetMapping("/{id}/enrollments")
	public Object enrollments(@PathVariable Long id) {
		return rest.getForObject(studentUrl + "/api/students/course/" + id, Object.class);
	}
}