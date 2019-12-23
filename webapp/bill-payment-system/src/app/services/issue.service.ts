import { Injectable, RootRenderer } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { AuthenticateService } from './authentication.service';
import { UserDashboardComponent } from '../customer/user-dashboard/user-dashboard.component';
import { User } from '../model/user.model';
import { Observable } from 'rxjs';
import { Issue } from '../model/issue.model';

@Injectable({
    providedIn:"root"
})
export class IssueService{

    user:User;


    constructor(private httpClient:HttpClient,private authenticateService:AuthenticateService){}

    reportIssue(issue:string):Observable<any>{
        this.user = this.authenticateService.userAuthenticated;
        console.log(this.user);
        
        
        return this.httpClient.post<any>("http://localhost:8090/issue/"+this.user.userId,issue);
    }


    getAllUnAnswered():Observable<any>{
        this.user = this.authenticateService.userAuthenticated;
        console.log(this.user);
        
        
        return this.httpClient.get<Issue[]>("http://localhost:8090/issue/unanswered");
    }
}