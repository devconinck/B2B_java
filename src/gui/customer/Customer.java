package gui.customer;

import java.time.LocalDate;

public class Customer {
	
	private String name;
	private int age;
	private LocalDate birthdate;
	
	public Customer(String name, int age, LocalDate birthdate) {
		this.name = name;
		this.age = age;
		this.birthdate = birthdate;
	}

	public String getName() {
		return name;
	}

	public int getAge() {
		return age;
	}

	public LocalDate getBirthdate() {
		return birthdate;
	}

}
