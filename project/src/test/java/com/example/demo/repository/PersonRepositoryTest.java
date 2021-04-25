package com.example.demo.repository;


import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.domain.Person;
import com.example.demo.domain.dto.Birthday;

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
	
	@Test
	void hashCodeAndEquals() {
		Person person1 = new Person("martin",10,"A");
		Person person2 = new Person("martin",10,"B");
		
		System.out.println(person1.equals(person2));
		System.out.println(person1.hashCode());
		System.out.println(person2.hashCode());
		
		Map<Person,Integer> map = new HashMap<>();
		map.put(person1, person1.getAge());
		
		System.out.println(map);
		System.out.println(map.get(person2));
	}
	
	@Test
	void findByBloodType() {
		givenPerson("martin", 10, "A");
		givenPerson("martin", 7, "AB");
		givenPerson("martin", 9, "O");
		givenPerson("sophia", 3, "A");
		
		List<Person> result = personRepository.findByBloodType("A");
		
		result.forEach(System.out::println);
	}
	
	@Test
	void findtest() {
		givenPerson("martin", 10, "A", LocalDate.of(1991, 8, 15));
		givenPerson("martin", 7, "AB", LocalDate.of(1992, 4, 26));
		givenPerson("martin", 9, "O", LocalDate.of(1993, 4, 25));
		givenPerson("sophia", 3, "A", LocalDate.of(1995, 4, 26));
		LocalDate tomorrow =LocalDate.now().plusDays(1);
		int tDay = tomorrow.getDayOfMonth();
		int tMon = tomorrow.getMonthValue();
		LocalDate now =LocalDate.now();
		int nDay = now.getDayOfMonth();
		int nMon = now.getMonthValue();
		 
		List<Person> result = personRepository.findByBirthday(nMon, nDay, tMon, tDay);
		result.forEach(System.out::println);
	}
	
	
	
	@Test
	void findByBirthdayBetween() {
		givenPerson("martin", 10, "A", LocalDate.of(1991, 8, 15));
		givenPerson("martin", 7, "AB", LocalDate.of(1992, 7, 10));
		givenPerson("martin", 9, "O", LocalDate.of(1993, 1, 5));
		givenPerson("sophia", 3, "A", LocalDate.of(1995, 5, 3));
		
		List<Person> result = personRepository.findByMonthOfBirthday(8);
		
		result.forEach(System.out::println);
	}
	
	private void givenPerson(String name,int age,String bloodType) {
		givenPerson(name ,age , bloodType);
	}
	
	
	
	private void givenPerson(String name,int age,String bloodType,LocalDate birthday) {
		Person person = new Person(name,age,bloodType);
		person.setBirthday(new Birthday(birthday));
		 
		personRepository.save(person);
	} 
	
}
 