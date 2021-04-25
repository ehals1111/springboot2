package com.example.demo.Service;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.domain.Block;
import com.example.demo.domain.Person;
import com.example.demo.repository.BlockRepository;
import com.example.demo.repository.PersonRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class PersonService {
	@Autowired
	private PersonRepository personRepository;
	
	@Autowired
	private BlockRepository blockRepository;
	
	public List<Person> getPeopleExcludeBlocks(){
		//List<Person> people= personRepository.findAll();
		//List<Block> blocks = blockRepository.findAll();
		//List<String> blockNames = blocks.stream().map(Block::getName).collect(Collectors.toList());
		
		//return people.stream().filter(person -> person.getBlock()== null).collect(Collectors.toList());
		return personRepository.findByBlockIsNull();
	}
	
	public List<Person> getPeopleByname(String name){
		//List<Person> people = personRepository.findAll();
		
		//return people.stream().filter(person -> person.getName().equals(name)).collect(Collectors.toList());
		return personRepository.findByName(name);
	}
	
	@Transactional(readOnly = true)
	public Person getPerson(Long id) {
		Person person = personRepository.findById(id).get();
		
		log.info("person: {}",person);
		
		return person;
		
	}
	
	public List<Person> getBirthdayList(int nMon, int nDay, int tMon, int tDay){
		
		return personRepository.findByBirthday(nMon, nDay, tMon, tDay);
	}
	
	
}
