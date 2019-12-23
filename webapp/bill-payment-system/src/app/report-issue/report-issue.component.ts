import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { IssueService } from '../services/issue.service';
import { Issue } from '../model/issue.model';
import { AuthenticateService } from '../services/authentication.service';

@Component({
  selector: 'app-report-issue',
  templateUrl: './report-issue.component.html',
  styleUrls: ['./report-issue.component.css']
})
export class ReportIssueComponent implements OnInit {

  reportIssueForm:FormGroup;

  issueReported:boolean=false;

  isFormSubmitted:boolean=false;

  isAdmin:boolean;

  constructor(private issueService:IssueService,private authService:AuthenticateService) { }

  ngOnInit() {
    this.getAllIssues()
    this.isAdmin=this.authService.isAdmin;
    this.reportIssueForm=new FormGroup({
      'issue':new FormControl(null,Validators.required)
    })
  }

  submitIssue(){
    
    this.issueService.reportIssue(this.reportIssueForm.get('issue').value).subscribe((data)=>{
      console.log(data);
      this.issueReported=true
      this.isFormSubmitted=true;
    });
  }

  reportIssue(){
    this.issueReported=false;
  }

  reportedIssues:Issue[]

  getAllIssues(){
    this.issueService.getAllUnAnswered().subscribe((data:Issue[])=>{
      this.reportedIssues=data;
      console.log(data)
      console.log(this.reportedIssues)
    });
    
  }

}
