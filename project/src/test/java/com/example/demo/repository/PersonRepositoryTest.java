package com.example.demo.repository;


import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.domain.Person;

@SpringBootTest
public class PersonRepositoryTest {
	@Autowired
	private PersonRepository personRepository;
	
	@Test 
	void crud() {
		Person person = new Person();
		person.setAge(10);
		person.setName("aa");
		person.setBloodType("A");
		
		personRepository.save(person);
		
		System.out.println(personRepository.findAll());
		
		List<Person> people = personRepository.findAll();
		
		assertThat(people.size()).isEqualTo(1);
		assertThat(people.get(0).getName()).isEqualTo("aa");
		assertThat(people.get(0).getAge()).isEqualTo(10);
		assertThat(people.get(0).getBloodType()).isEqualTo("A");
	}
}
 