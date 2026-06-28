package com.idream.course;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(CourseController.class)
class CourseControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private CourseRepository courseRepository;

	@Test
	void shouldReturnAllCourses() throws Exception {

		Course c1 = new Course(1L, "DevOps with AWS", "Git, Jenkins, Docker, Kubernetes", "3 Months");

		Course c2 = new Course(2L, "Java Full Stack", "React, Spring Boot and MySQL", "4 Months");

		when(courseRepository.findAll()).thenReturn(List.of(c1, c2));

		mockMvc.perform(get("/api/courses")).andExpect(status().isOk()).andExpect(jsonPath("$", hasSize(2)))
				.andExpect(jsonPath("$[0].name").value("DevOps with AWS"))
				.andExpect(jsonPath("$[1].name").value("Java Full Stack"));
	}
}