package com.example.demo.repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.domain.Person;

public interface PersonRepository extends JpaRepository<Person, Long>{

	List<Person> findByName(String name);
	
	List<Person> findByBlockIsNull();//블럭인 필드가 Null인 경우 펄슨을 다 가져온다

	List<Person> findByBloodType(String bloodType);
	
	@Query(value="select person from Person person where person.birthday.monthOfBirthday=?1")//jpql  nativeQuery=true;
	List<Person> findByMonthOfBirthday(int monthOfBirthday);
	
	@Query(value="select * from person where month_Of_Birthday= :nMon and day_of_birthday= :nDay or month_Of_Birthday= :tMon and day_of_birthday= :tDay",nativeQuery=true)//jpql  nativeQuery=true;
	List<Person> findByBirthday(@Param("nMon")int nMon ,@Param("nDay") int nDay,@Param("tMon") int tMon,@Param("tDay") int tDay);
	//List<Person> findBy
}
 