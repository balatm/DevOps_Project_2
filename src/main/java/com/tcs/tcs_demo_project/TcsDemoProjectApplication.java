package com.tcs.tcs_demo_project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@SpringBootApplication
public class TcsDemoProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(TcsDemoProjectApplication.class, args);
	}

	@GetMapping("/")
	public String Hello() {
		return "<h1>Welcome TCS DevOps Certification!</h1>";
	}
}
