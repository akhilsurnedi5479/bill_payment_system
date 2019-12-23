import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  constructor() { }

  admin_color:string ="#326699";
  ven_color="#326699";
  cust_color="";
  admin_t_color:string ="#ffffff";
  ven_t_color="#ffffff";
  cust_t_color="black";
  isAdmin:boolean=false;
  isCust:boolean=true;
  isVen:boolean=false;
  
  

  ngOnInit() {
  }

  adminSignUp(){
    this.isAdmin=true;
    this.isCust=false;
    this.isVen=false;
    this.admin_color="";
    this.cust_color="#326699";
    this.ven_color="#326699";

    this.admin_t_color="black";
    this.cust_t_color="#ffffff";
    this.ven_t_color="#ffffff";
    
  }

  custSignUp(){
    this.isAdmin=false;
    this.isCust=true;
    this.isVen=false;
    this.admin_color="#326699";
    this.cust_color="";
    this.ven_color="#326699";

    this.admin_t_color="#ffffff";
    this.cust_t_color="black";
    this.ven_t_color="#ffffff";
  }
  venSignUp(){
    this.isAdmin=false;
    this.isCust=false;
    this.isVen=true;
    this.admin_color="#326699";
    this.cust_color="#326699";
    this.ven_color="";

    this.admin_t_color="#ffffff";
    this.cust_t_color="#ffffff";
    this.ven_t_color="black";
  }
}
