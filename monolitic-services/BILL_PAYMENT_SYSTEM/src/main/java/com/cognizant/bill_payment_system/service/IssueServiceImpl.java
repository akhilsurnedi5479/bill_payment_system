package com.cognizant.bill_payment_system.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cognizant.bill_payment_system.model.Issue;
import com.cognizant.bill_payment_system.model.User;
import com.cognizant.bill_payment_system.repository.IssueRepository;
import com.cognizant.bill_payment_system.repository.UserRepository;


@Service
public class IssueServiceImpl implements IssueService{

	@Autowired
	UserRepository userRepository;
	
	
	@Autowired
	IssueRepository issueRepo;
	
	@Override
	public void reportIssue(String username, String issue) {
		System.out.println("issue Report");
		User user = userRepository.findByUserId(username);
		Issue newIssue = new Issue();
		newIssue.setUser(user);
		newIssue.setIssue(issue);
		newIssue.setStatus(0);
		issueRepo.save(newIssue);
	}

	@Override
	public List<Issue>getAllIssues(int status) {
		
		return issueRepo.findAll();
		
	}
	

}
