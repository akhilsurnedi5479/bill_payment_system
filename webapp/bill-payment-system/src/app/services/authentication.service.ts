import { Injectable } from '@angular/core';
import { UserService } from './user.service';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { User } from '../model/user.model';

@Injectable({
  providedIn: 'root'
})
export class AuthenticateService {
  loggedIn=false;
  isAdmin=false;
  isVendor=false;
  authSource:string;
  redirectUrl='/';
  userAuthenticated:User;
  accessToken: string;
  role:string;
  constructor(private userService:UserService,private http:HttpClient) { }

  authenticate(username:string,password:string):Observable<any>{
    let credentials = btoa(username + ':' + password);
    let headers = new HttpHeaders();
    headers = headers.set('Authorization', 'Basic ' + credentials);
    return this.http.get("http://localhost:8090/authenticate", { headers });
  }
  logOut(){
    this.redirectUrl='/';
    this.loggedIn=false;
    this.isAdmin=false;
    this.isVendor=false;
  }
  isAdminUser(){
    return this.isAdmin;
  }

  isVendorUser(){
    return this.isVendor;
  }
}
