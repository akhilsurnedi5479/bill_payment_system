package com.cognizant.bill_payment_system.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cognizant.bill_payment_system.model.Issue;
import com.cognizant.bill_payment_system.service.IssueService;
import com.cognizant.bill_payment_system.service.UserService;

@RestController
@RequestMapping("/issue")
public class IssueController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	IssueService issueService;
	
	
	@PostMapping("/{username}")
	public void reportIssue(@PathVariable String username,@RequestBody String issue) {
		System.out.println("issue"+issue);
		issueService.reportIssue(username,issue);
	}
	
	@GetMapping("/unanswered")
	public List<Issue> getAllUnAnsweredIssues() {
		List<Issue> issues = issueService.getAllIssues(0);
		System.out.println(issues);
		return issues;
	}
}
