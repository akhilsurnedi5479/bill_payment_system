import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { NgForm } from '@angular/forms';
import { AuthenticateService } from 'src/app/services/authentication.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  isLoginValid = true;
  authSource: string = 'customer';
  constructor(private router: Router, private route: ActivatedRoute, private authenticateService: AuthenticateService) {
  }

  ngOnInit() {
    this.authSource = this.authenticateService.authSource;
    if (this.authenticateService.role === 'ROLE_USER') {
      this.authenticateService.redirectUrl = "/vendors";
      this.router.navigate([this.authenticateService.redirectUrl]);
    } else if(this.authenticateService.role === 'ROLE_ADMIN'){
      this.authenticateService.redirectUrl = "/vendor-approval";
      this.router.navigate([this.authenticateService.redirectUrl]);
    }else{
      this.authenticateService.redirectUrl = "/vendor-edit";
      this.router.navigate([this.authenticateService.redirectUrl]);
    }
  }

  onSubmit(form: NgForm) {
    const username = form.value.uname;
    const password = form.value.pass;
    if (username == 'john123') {
      this.isLoginValid = false;
    } else {
      this.authSource = 'customer';
      this.authenticateService.authenticate(username, password).subscribe(data => {
          this.authenticateService.accessToken = data['token'];
          this.authenticateService.role = data['role'];
          if (this.authenticateService.role === 'ROLE_USER') {
            this.authenticateService.isAdmin = false;
            this.authenticateService.isVendor=false;
            this.authenticateService.userAuthenticated = {userId:username,firstName:username,lastName:username,role:'customer'};
            this.authenticateService.redirectUrl = "/vendors";
            this.router.navigate([this.authenticateService.redirectUrl]);
          } else if(this.authenticateService.role === 'ROLE_ADMIN'){
            this.authenticateService.isAdmin = true;
            this.authenticateService.isVendor=false;
            this.authenticateService.userAuthenticated = {userId:"admin",firstName:"admin",lastName:"admin",role:'admin'}
            this.authenticateService.redirectUrl = "/vendor-approval";
            this.authenticateService.loggedIn = true;
            this.router.navigate([this.authenticateService.redirectUrl]);
          }else{
            this.authenticateService.isAdmin=false;
            this.authenticateService.isVendor=true;
            this.authenticateService.userAuthenticated = {userId:username,firstName:username,lastName:username,role:'vendor'}
            this.authenticateService.redirectUrl = "/vendor-edit";
            this.router.navigate([this.authenticateService.redirectUrl]);
          }
          this.authenticateService.loggedIn = true;
          
      },
      (error)=>{
        if(error['status']===401){
          this.isLoginValid=false;
        }
      }
      );;
    }
  }

}
