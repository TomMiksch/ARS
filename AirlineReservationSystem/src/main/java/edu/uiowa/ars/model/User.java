package edu.uiowa.ars.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;

@Entity
@Table(name = "USER")
public final class User {

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

	@Size(min = 3, max = 50)
	@Column(name = "EMAIL", nullable = false)
	private String emailAddress;
	
	@Column(name = "PASSWORD", nullable = false)
	private String password;
        
       /*
        @Column(name = "DOB", nullable = false)
        private Date dob;*/
        
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
        
       /* public Date getDOB() {
            return dob;
        }
        
        public void setDOB(final Date dob) {
            this.dob = dob;
        }*/
        
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