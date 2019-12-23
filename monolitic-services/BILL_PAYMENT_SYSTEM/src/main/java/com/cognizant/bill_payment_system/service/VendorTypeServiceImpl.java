package com.cognizant.bill_payment_system.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cognizant.bill_payment_system.model.VendorDetails;
import com.cognizant.bill_payment_system.model.VendorType;
import com.cognizant.bill_payment_system.repository.VendorTypeRepository;

@Service
public class VendorTypeServiceImpl implements VendorTypeService{

	@Autowired
	VendorTypeRepository vendorTypeRepository;
	
	@Override
	public List<VendorDetails> getAllVendorTypes() {
		List<VendorType> vendorTypeList=vendorTypeRepository.findAll();
		List<VendorDetails> vendorDetailsList=new ArrayList<VendorDetails>();
		VendorDetails vendorDetails;
		for(VendorType vendorType:vendorTypeList) {
			vendorDetails=new VendorDetails();
			vendorDetails.setVendorType(vendorType.getVendorTypeName());
			vendorDetails.setVendorImage(vendorType.getVendorImage());
			vendorDetailsList.add(vendorDetails);
		}
		return vendorDetailsList;
	}
}
