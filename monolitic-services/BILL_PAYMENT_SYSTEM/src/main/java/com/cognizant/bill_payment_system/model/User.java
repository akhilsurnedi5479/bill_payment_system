package com.cognizant.bill_payment_system.model;

import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;

import com.fasterxml.jackson.annotation.JsonIgnore;



@Entity
@Table(name="user",uniqueConstraints= @UniqueConstraint(columnNames = { "us_id","us_user_id" }))
public class User {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="us_id")
	private int id;
	
	@Column(name="us_first_name")
	private String firstName;
	
	@Column(name="us_last_name")
	private String lastName;
	
	@Column(name="us_age")
	private int age;
	
	@Column(name="us_gender")
	private String gender;
	
	@Column(name="us_contact_number")
	private long contactNumber;
	
	@Column(name="us_pan_number")
	private String panNumber;
	
	@Column(name="us_aadhar_number")
	private String aadharNumber;
	
	@Column(name="us_user_id")
	private String userId;
	
	@Column(name="us_password")
	private String password;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="us_role_id")
	private Role role;
	
	@Transient
	boolean admin =false;
	
	//many to many from users to vendors;
	@JsonIgnore
	@ManyToMany(fetch=FetchType.EAGER)
	@JoinTable(name="user_vendor",joinColumns=@JoinColumn(name="uv_us_id"),inverseJoinColumns=@JoinColumn(name="uv_vn_id"))
	private List<Vendor> vendorList;

	@JsonIgnore
	@OneToMany(mappedBy="user")
	private List<Issue> issue;
	
	
	
	public List<Issue> getIssue() {
		return issue;
	}

	public void setIssue(List<Issue> issue) {
		this.issue = issue;
	}

	public User() {
		super();
		// TODO Auto-generated constructor stub
	}

	public User(int id, String firstName, String lastName, int age, String gender, long contactNumber, String panNumber,
			String aadharNumber, String userId, String password) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.age = age;
		this.gender = gender;
		this.contactNumber = contactNumber;
		this.panNumber = panNumber;
		this.aadharNumber = aadharNumber;
		this.userId = userId;
		this.password = password;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public long getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(long contactNumber) {
		this.contactNumber = contactNumber;
	}

	public String getPanNumber() {
		return panNumber;
	}

	public void setPanNumber(String panNumber) {
		this.panNumber = panNumber;
	}

	public String getAadharNumber() {
		return aadharNumber;
	}

	public void setAadharNumber(String aadharNumber) {
		this.aadharNumber = aadharNumber;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	

	public Role getRole() {
		return role;
	}

	public boolean isAdmin() {
		return admin;
	}

	public void setAdmin(boolean admin) {
		this.admin = admin;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public List<Vendor> getVendorList() {
		return vendorList;
	}

	public void setVendorList(List<Vendor> vendorList) {
		this.vendorList = vendorList;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", age=" + age + ", gender="
				+ gender + ", contactNumber=" + contactNumber + ", panNumber=" + panNumber + ", aadharNumber="
				+ aadharNumber + ", userId=" + userId + ", password=" + password + "]";
	}
	
	
	
	
	
}
