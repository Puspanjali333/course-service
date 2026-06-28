package com.idream.course;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class CourseServiceApplication {
	public static void main(String[] a) {
		SpringApplication.run(CourseServiceApplication.class, a);
	}

	@Bean
	RestTemplate restTemplate() {
		return new RestTemplate();
	}

	@Bean
	CommandLineRunner init(CourseRepository r) {
		return args -> {
			if (r.count() == 0) {
				r.save(new Course(null, "DevOps with AWS", "Git, Jenkins, Docker, Kubernetes, Terraform and AWS CI/CD demo", "3 Months"));
				r.save(new Course(null, "Java Full Stack", "React UI with Spring Boot microservices and MySQL", "4 Months"));
				r.save(new Course(null, "Cloud Computing", "AWS, Azure and GCP practical cloud projects", "3 Months"));
			}
		};
	}
}
