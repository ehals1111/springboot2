package com.example.demo.domain;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
public class Person {
	
	@Id
	@GeneratedValue
	private Long id;
	
	
	private String name;
	
	
	private int age;
	
	
	private String hobby;
	
	
	private String bloodType;
	
	
	private String address;
	
	
	private LocalDateTime birthday;
	
	
	private String job;
	
	@ToString.Exclude //toString delete
	private String phoneNumber;



	
}
