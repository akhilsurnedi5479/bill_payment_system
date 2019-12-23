package com.cognizant.bill_payment_system.model;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

public class VendorDetails {
	String vendorName;
	String vendorRegNo;
	
	//
	String vendorType;
	String password;
	String vendorAddress;
	String vendorCountry;
	String vendorState;
	String vendorEmail;
	long vendorContactNo;
	String vendorWebSite;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	Date vendorCertificationIssueDate;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	Date vendorCertificationValidityDate;
	int vendorYearOfEstablishment;
	
	String vendorImage;
	
	int status;
	//
	List<String> paymentGateway;
	
	
	public VendorDetails() {
		super();
		// TODO Auto-generated constructor stub
	}
	public VendorDetails(String vendorName, String vendorRegNo, String vendorType, String password,
			String vendorAddress, String vendorCountry, String vendorState, String vendorEmail, long vendorContactNo,
			String vendorWebSite, Date vendorCertificationIssueDate, Date vendorCertificationValidityDate,
			int vendorYearOfEstablishment, List<String> paymentGateway,int status) {
		super();
		this.vendorName = vendorName;
		this.vendorRegNo = vendorRegNo;
		this.vendorType = vendorType;
		this.password = password;
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
		this.status=status;
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
	public String getVendorType() {
		return vendorType;
	}
	public void setVendorType(String vendorType) {
		this.vendorType = vendorType;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
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
	public List<String> getPaymentGateway() {
		return paymentGateway;
	}
	public void setPaymentGateway(List<String> paymentGateway) {
		this.paymentGateway = paymentGateway;
	}
	

	public String getVendorImage() {
		return vendorImage;
	}
	public void setVendorImage(String vendorImage) {
		this.vendorImage = vendorImage;
	}
	@Override
	public String toString() {
		return "VendorDetails [vendorName=" + vendorName + ", vendorRegNo=" + vendorRegNo + ", vendorType=" + vendorType
				+ ", password=" + password + ", vendorAddress=" + vendorAddress + ", vendorCountry=" + vendorCountry
				+ ", vendorState=" + vendorState + ", vendorEmail=" + vendorEmail + ", vendorContactNo="
				+ vendorContactNo + ", vendorWebSite=" + vendorWebSite + ", vendorCertificationIssueDate="
				+ vendorCertificationIssueDate + ", vendorCertificationValidityDate=" + vendorCertificationValidityDate
				+ ", vendorYearOfEstablishment=" + vendorYearOfEstablishment + ", vendorImage=" + vendorImage
				+ ", paymentGateway=" + paymentGateway + "]";
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	
	
	
	
}
