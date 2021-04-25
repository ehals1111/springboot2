package com.example.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class HelloWorldController {
	
	@GetMapping(value = "/api/helloWorld")
	public String helloWord() {
		return "HelloWorld";
	}
}