package com.cognizant.bill_payment_system.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="issue")
public class Issue {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="issue_id")
	private int issueId;
	
	@Column(name="issue")
	private String issue;
	
	@Column(name="answer")
	private String answer;
	
	@ManyToOne
	@JoinColumn(name="issue_user_id")
	private User user;
	
	@JoinColumn(name="issue_status")
	private int status;

	public Issue() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	
	public Issue(int issueId, String issue, String answer, User user, int status) {
		super();
		this.issueId = issueId;
		this.issue = issue;
		this.answer = answer;
		this.user = user;
		this.status = status;
	}



	public int getStatus() {
		return status;
	}



	public void setStatus(int status) {
		this.status = status;
	}



	public int getIssueId() {
		return issueId;
	}

	public void setIssueId(int issueId) {
		this.issueId = issueId;
	}

	public String getIssue() {
		return issue;
	}

	public void setIssue(String issue) {
		this.issue = issue;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	
	 
}
