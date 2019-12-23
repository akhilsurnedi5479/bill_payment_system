import { Component, OnInit } from '@angular/core';
import { AuthenticateService } from 'src/app/services/authentication.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {

  constructor(private router:Router,private authenticateService:AuthenticateService) { }

  ngOnInit() {
  }
  isAuthenticated(){
   // this.authenticateService.authSource="customer";
   
    return this.authenticateService.loggedIn;
  }
  isAdmin(){
    return this.authenticateService.isAdminUser();
  }

  isVendor(){
    return this.authenticateService.isVendorUser();
  }
  getUser(){
    return this.authenticateService.userAuthenticated;
  }
  onSignOut(){
    this.authenticateService.logOut();
    this.router.navigate([this.authenticateService.redirectUrl]);
  }
}
