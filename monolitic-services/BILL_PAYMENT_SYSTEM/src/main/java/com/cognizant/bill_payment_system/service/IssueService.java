package com.cognizant.bill_payment_system.service;

import java.util.List;

import com.cognizant.bill_payment_system.model.Issue;

public interface IssueService {
	public void reportIssue(String username,String issue);
	
	public List<Issue> getAllIssues(int status);
	
	
}
