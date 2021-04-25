package com.example.demo.service;

import java.time.LocalDate;
import java.util.List;



import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.Service.PersonService;
import com.example.demo.domain.Block;
import com.example.demo.domain.Person;
import com.example.demo.repository.BlockRepository;
import com.example.demo.repository.PersonRepository;

@SpringBootTest
public class PersonServiceTest {
	@Autowired
	private PersonService personService;
	
	@Autowired
	private PersonRepository personRepository;
	
	@Autowired
	private BlockRepository blockRepository;
	
	@Test
	void getPeopleExcludeBlocks() {
		givenPeople();
	
		
		List<Person> result = personService.getPeopleExcludeBlocks();
		//System.out.println(result);
		result.forEach(System.out::println);
	}
	
	@Test
	void getPeopleByName() {
		givenPeople();
		List<Person> result = personService.getPeopleByname("martin");
		
		result.forEach(System.out::println);
	}
	
	@Test
	void cascadeTest() {
		givenPeople();
		
		List<Person> result = personRepository.findAll();
		
		result.forEach(System.out::println);
		
		Person person = result.get(3);
		person.getBlock().setStartDate(LocalDate.now());
		person.getBlock().setEndDate(LocalDate.now());
		
		personRepository.save(person);
		personRepository.findAll().forEach(System.out::println);
		
		//personRepository.delete(person);
		//personRepository.findAll().forEach(System.out::println);
		//blockRepository.findAll().forEach(System.out::println);
		
		person.setBlock(null);
		personRepository.save(person);
		personRepository.findAll().forEach(System.out::println);
		
		blockRepository.findAll().forEach(System.out::println);
	}
	 
	@Test
	void getPerson() {
		givenPeople();
		Person person = personService.getPerson(3L);
		
		
	}
	
	private void givenBlocks() {
		givenBlock("martin");
	}
	private void givenPeople() {
		givenPerson("martin",10,"A");
		givenPerson("david",10,"A");
		givenBlockPerson("denis",10,"A");
		givenBlockPerson("maa",10,"A");
		givenBlockPerson("martin",10,"A");
	}
	
	private void givenPerson(String name, int age,String bloodType) {
		personRepository.save(new Person(name,age,bloodType));
	}
	private void givenBlockPerson(String name,int age, String bloodType) {
		Person blockPerson = new Person(name, age, bloodType);
		
		blockPerson.setBlock(new Block(name));
		
		personRepository.save(blockPerson);
	}
	 
	
	private Block givenBlock(String name) {
		
		return blockRepository.save(new Block(name));
	}
}
