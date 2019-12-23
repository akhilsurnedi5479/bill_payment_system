package com.cognizant.bill_payment_system.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="payment_gateway")
public class Payment_Gateway {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="pg_id")
	private int paymentGatewayId;
	
	@Column(name="pg_name")
	private String paymentGatewayName;
	
	//Many to many from vendor
	@JsonIgnore
	@ManyToMany(mappedBy="paymentGateway")
	private List<Vendor> vendorList;

	public Payment_Gateway() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Payment_Gateway(int paymentGatewayId, String paymentGatewayName) {
		super();
		this.paymentGatewayId = paymentGatewayId;
		this.paymentGatewayName = paymentGatewayName;
	}

	public int getPaymentGatewayId() {
		return paymentGatewayId;
	}

	public void setPaymentGatewayId(int paymentGatewayId) {
		this.paymentGatewayId = paymentGatewayId;
	}

	public String getPaymentGatewayName() {
		return paymentGatewayName;
	}

	public void setPaymentGatewayName(String paymentGatewayName) {
		this.paymentGatewayName = paymentGatewayName;
	}

	public List<Vendor> getVendorList() {
		return vendorList;
	}

	public void setVendorList(List<Vendor> vendorList) {
		this.vendorList = vendorList;
	}

	@Override
	public String toString() {
		return "Payment_Gateway [paymentGatewayId=" + paymentGatewayId + ", paymentGatewayName=" + paymentGatewayName
				+ ", vendorList=" + vendorList + "]";
	}
	
}
