package com.cognizant.bill_payment_system.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cognizant.bill_payment_system.model.Issue;

public interface IssueRepository extends JpaRepository<Issue,Integer>{

	
}
