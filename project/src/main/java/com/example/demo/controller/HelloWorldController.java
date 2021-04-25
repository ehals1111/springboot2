package com.example.demo.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Service.PersonService;
import com.example.demo.domain.Person;


@RestController
public class HelloWorldController {
	
	@Autowired
	private PersonService personService;
	
	@GetMapping(value = "/api/helloWorld")
	public String helloWord() {
		return "HelloWorld";
	}
	
	
	
	//PersonRepositoryTest.java ->  findtest() test�� �غ� ��� �� ���Խ��ϴٸ� ���� ���ڸ�j�� �ƴϴ� ���� sql���̶�簡 �׽�Ʈ���Ҽ��� �������ϴ�. ���λ����� ���� ������ �غ��ϴ�.
	@GetMapping(value="/api/person/birthday-friends")
	public List<Person>  personBirthday() {
		
		LocalDate tomorrow =LocalDate.now().plusDays(1);
		int tDay = tomorrow.getDayOfMonth();
		int tMon = tomorrow.getMonthValue();
		LocalDate now =LocalDate.now();
		int nDay = now.getDayOfMonth();
		int nMon = now.getMonthValue();
		
		return personService.getBirthdayList(nMon, nDay, tMon, tDay); 
	}
}
