package edu.uiowa.ars.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;

@Entity
@Table(name = "EMPLOYEE")
public final class Employee {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Size(min = 1, max = 20)
	@Column(name = "FIRST_NAME", nullable = false)
	private String firstName;

	@Size(min = 1, max = 20)
	@Column(name = "LAST_NAME", nullable = false)
	private String lastName;

	@Size(min = 3, max = 50)
	@Column(name = "EMAIL", nullable = false)
	private String emailAddress;

	public int getId() {
		return id;
	}

	public void setId(final int id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(final String name) {
		this.firstName = name;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(final String name) {
		this.lastName = name;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(final String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public String getFullName() {
		return firstName + " " + lastName;
	}
}