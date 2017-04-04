package edu.uiowa.ars.model;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "USER")
public final class User {

	public enum UserTypes {
		ADMINISTRATOR("Admin"),
		EMPLOYEE("Employee"),
		CUSOMTER("Customer");

		private final String identifier;

		UserTypes(final String identifier) {
			this.identifier = identifier;
		}

		public String getIdentifier() {
			return this.identifier;
		}

		public static List<String> getAllIdentifiers() {
			return Stream.of(UserTypes.values()).map(UserTypes::getIdentifier).collect(Collectors.toList());
		}
	}

	public enum Genders {
		MALE("Male"),
		FEMALE("Female");

		private final String identifier;

		Genders(final String identifier) {
			this.identifier = identifier;
		}

		public String getIdentifier() {
			return this.identifier;
		}

		public static List<String> getAllIdentifiers() {
			return Stream.of(Genders.values()).map(Genders::getIdentifier).collect(Collectors.toList());
		}
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "USER_TYPE", nullable = false)
	private String userType;

	@Size(min = 1, max = 20)
	@Column(name = "FIRST_NAME", nullable = false)
	private String firstName;

	@Size(min = 1, max = 20)
	@Column(name = "LAST_NAME", nullable = false)
	private String lastName;

	@Column(name = "EMAIL", nullable = false)
	private String emailAddress;

	@Column(name = "PASSWORD", nullable = false)
	private String password;
        
        @Column(name = "PASSWORDHOLDER", nullable = false)
        private String passwordHolder;

	@Size(min = 4, max = 4)
	@Column(name = "DOBY", nullable = false)
	private String dobY;

	@Size(min = 1, max = 2)
	@Column(name = "DOBM", nullable = false)
	private String dobM;

	@Size(min = 1, max = 2)
	@Column(name = "DOBD", nullable = false)
	private String dobD;

	@Column(name = "GENDER", nullable = false)
	private String gender;

	@Size(min = 10, max = 10)
	@Column(name = "PHONE_NUMBER", nullable = false)
	private String phoneNumber;

	public int getId() {
		return id;
	}

	public void setId(final int id) {
		this.id = id;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(final String userType) {
		this.userType = userType;
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

	public String getPassword() {
		return password;
	}

	public void setPassword(final String password) {
		this.password = password;
	}
        
        public String getPasswordHolder() {
            return passwordHolder;
        }
        
        public void setPasswordHolder(final String passwordHolder) {
            this.passwordHolder = passwordHolder;
        }

	public String getDobY() {
		return dobY;
	}

	public void setDobY(final String dobY) {
		this.dobY = dobY;
	}

	public String getDobM() {
		return dobM;
	}

	public void setDobM(final String dobM) {
		this.dobM = dobM;
	}

	public String getDobD() {
		return dobD;
	}

	public void setDobD(final String dobD) {
		this.dobD = dobD;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(final String gender) {
		this.gender = gender;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(final String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getFullName() {
		return firstName + " " + lastName;
	}
}
