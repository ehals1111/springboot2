package com.example.demo.domain.dto;

import java.time.LocalDate;

import javax.persistence.Embeddable;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable //엔티티에 속해 있는 DTO
@NoArgsConstructor
@Data
public class Birthday {
	private int yearOfBirthday;
	
	
	@Min(1)
	@Max(12)
	private int monthOfBirthday;
	
	@Min(1)
	@Max(31)
	private int dayOfBirthday;
	
	
	
	public Birthday(LocalDate birthday) {
		this.yearOfBirthday =birthday.getYear();
		this.monthOfBirthday = birthday.getMonthValue();
		this.dayOfBirthday = birthday.getDayOfMonth();
	}
}
