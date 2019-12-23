package com.cognizant.bill_payment_system.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="vendor_type")
public class VendorType {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="vn_ty_id")
	private int vendorId;
	
	@Column(name="ven_ty_name")
	private String vendorTypeName;
	
	@Column(name="ven_img")
	private String vendorImage;
	
	

	public String getVendorImage() {
		return vendorImage;
	}

	public void setVendorImage(String vendorImage) {
		this.vendorImage = vendorImage;
	}

	@JsonIgnore
	@OneToMany(mappedBy="vendorType")
	private List<Vendor> vendorList;

	public VendorType() {
		super();
		// TODO Auto-generated constructor stub
	}

	public VendorType(int vendorId, String vendorTypeName, List<Vendor> vendorList) {
		super();
		this.vendorId = vendorId;
		this.vendorTypeName = vendorTypeName;
		this.vendorList = vendorList;
	}

	public int getVendorId() {
		return vendorId;
	}

	public void setVendorId(int vendorId) {
		this.vendorId = vendorId;
	}

	public String getVendorTypeName() {
		return vendorTypeName;
	}

	public void setVendorTypeName(String vendorTypeName) {
		this.vendorTypeName = vendorTypeName;
	}

	public List<Vendor> getVendorList() {
		return vendorList;
	}

	public void setVendorList(List<Vendor> vendorList) {
		this.vendorList = vendorList;
	}

	@Override
	public String toString() {
		return "VendorType [vendorId=" + vendorId + ", vendorTypeName=" + vendorTypeName + ", vendorList=" + vendorList
				+ "]";
	}
	
	
	
	
}
