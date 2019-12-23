package com.cognizant.bill_payment_system.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="vendor")
public class Vendor {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="vn_id")
	private int id;
	
	@Column(name="vn_name")
	private String vendorName;
	
	@Column(name="vn_reg_no")
	private String vendorRegNo;
	
	
	@ManyToOne
	@JoinColumn(name="vn_type")
	private VendorType vendorType;
	
	@Column(name="vn_address")
	private String vendorAddress;
	
	@Column(name="vn_country")
	private String vendorCountry;
	
	@Column(name="vn_state")
	private String vendorState;
	
	@Column(name="vn_email")
	private String vendorEmail;
	
	@Column(name="vn_contact_number")
	private long vendorContactNo;
	
	@Column(name="vn_website")
	private String vendorWebSite;
	
	@Column(name="vn_certificate_issue_date")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private Date vendorCertificationIssueDate;
	
	@Column(name="vn_certificate_validity_date")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private Date vendorCertificationValidityDate;
	
	@Column(name="vn_estd_date")
	private int vendorYearOfEstablishment;
	
	@ManyToMany
	@JoinTable(name="vendor_payment_gateway" ,joinColumns=@JoinColumn(name="vp_vn_id"),inverseJoinColumns=@JoinColumn(name="vp_pg_id"))
	private List<Payment_Gateway> paymentGateway;
	
	@JsonIgnore
	@ManyToMany(mappedBy="vendorList")
	private List<User> userList;
	
	@JsonIgnore
	@OneToMany(mappedBy="vendor")
	private List<Bill> billList;
	
	@Column(name="status")
	private int flag;

	public int getFlag() {
		return flag;
	}



	public void setFlag(int flag) {
		this.flag = flag;
	}



	public Vendor() {
		super();
		// TODO Auto-generated constructor stub
	}



	public Vendor(int id, String vendorName, String vendorRegNo, VendorType vendorType, String vendorAddress,
			String vendorCountry, String vendorState, String vendorEmail, long vendorContactNo, String vendorWebSite,
			Date vendorCertificationIssueDate, Date vendorCertificationValidityDate, int vendorYearOfEstablishment,
			List<Payment_Gateway> paymentGateway, List<User> userList, List<Bill> billList,int flag) {
		super();
		this.id = id;
		this.vendorName = vendorName;
		this.vendorRegNo = vendorRegNo;
		this.vendorType = vendorType;
		this.vendorAddress = vendorAddress;
		this.vendorCountry = vendorCountry;
		this.vendorState = vendorState;
		this.vendorEmail = vendorEmail;
		this.vendorContactNo = vendorContactNo;
		this.vendorWebSite = vendorWebSite;
		this.vendorCertificationIssueDate = vendorCertificationIssueDate;
		this.vendorCertificationValidityDate = vendorCertificationValidityDate;
		this.vendorYearOfEstablishment = vendorYearOfEstablishment;
		this.paymentGateway = paymentGateway;
		this.userList = userList;
		this.billList = billList;
		this.flag=flag;
	}



	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
	}



	public String getVendorName() {
		return vendorName;
	}



	public void setVendorName(String vendorName) {
		this.vendorName = vendorName;
	}



	public String getVendorRegNo() {
		return vendorRegNo;
	}



	public void setVendorRegNo(String vendorRegNo) {
		this.vendorRegNo = vendorRegNo;
	}



	public VendorType getVendorType() {
		return vendorType;
	}



	public void setVendorType(VendorType vendorType) {
		this.vendorType = vendorType;
	}



	public String getVendorAddress() {
		return vendorAddress;
	}



	public void setVendorAddress(String vendorAddress) {
		this.vendorAddress = vendorAddress;
	}



	public String getVendorCountry() {
		return vendorCountry;
	}



	public void setVendorCountry(String vendorCountry) {
		this.vendorCountry = vendorCountry;
	}



	public String getVendorState() {
		return vendorState;
	}



	public void setVendorState(String vendorState) {
		this.vendorState = vendorState;
	}



	public String getVendorEmail() {
		return vendorEmail;
	}



	public void setVendorEmail(String vendorEmail) {
		this.vendorEmail = vendorEmail;
	}



	


	public long getVendorContactNo() {
		return vendorContactNo;
	}



	public void setVendorContactNo(long vendorContactNo) {
		this.vendorContactNo = vendorContactNo;
	}



	public String getVendorWebSite() {
		return vendorWebSite;
	}



	public void setVendorWebSite(String vendorWebSite) {
		this.vendorWebSite = vendorWebSite;
	}



	public Date getVendorCertificationIssueDate() {
		return vendorCertificationIssueDate;
	}



	public void setVendorCertificationIssueDate(Date vendorCertificationIssueDate) {
		this.vendorCertificationIssueDate = vendorCertificationIssueDate;
	}



	public Date getVendorCertificationValidityDate() {
		return vendorCertificationValidityDate;
	}



	public void setVendorCertificationValidityDate(Date vendorCertificationValidityDate) {
		this.vendorCertificationValidityDate = vendorCertificationValidityDate;
	}



	public int getVendorYearOfEstablishment() {
		return vendorYearOfEstablishment;
	}



	public void setVendorYearOfEstablishment(int vendorYearOfEstablishment) {
		this.vendorYearOfEstablishment = vendorYearOfEstablishment;
	}



	public List<Payment_Gateway> getPaymentGateway() {
		return paymentGateway;
	}



	public void setPaymentGateway(List<Payment_Gateway> paymentGateway) {
		this.paymentGateway = paymentGateway;
	}



	public List<User> getUserList() {
		return userList;
	}



	public void setUserList(List<User> userList) {
		this.userList = userList;
	}



	public List<Bill> getBillList() {
		return billList;
	}



	public void setBillList(List<Bill> billList) {
		this.billList = billList;
	}



	@Override
	public String toString() {
		return "Vendor [id=" + id + ", vendorName=" + vendorName + ", vendorRegNo=" + vendorRegNo + ", vendorType="
				+ vendorType + ", vendorAddress=" + vendorAddress + ", vendorCountry=" + vendorCountry
				+ ", vendorState=" + vendorState + ", vendorEmail=" + vendorEmail + ", contactNumber=" + vendorContactNo
				+ ", vendorWebSite=" + vendorWebSite + ", vendorCertificationIssueDate=" + vendorCertificationIssueDate
				+ ", vendorCertificationValidityDate=" + vendorCertificationValidityDate
				+ ", vendorYearOfEstablishment=" + vendorYearOfEstablishment + ", paymentGateway=" + paymentGateway
				+ "]";
	}

	
	
	
}
