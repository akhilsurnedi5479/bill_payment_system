import { Component, OnInit } from '@angular/core';
import { FormControl, Validators, FormGroup } from '@angular/forms';
import { UserService } from 'src/app/services/user.service';
import { VendorService } from 'src/app/services/vendor.service';
import { AuthenticateService } from 'src/app/services/authentication.service';
import { Vendor } from 'src/app/model/vendor.model';
import {  CountriesService } from 'src/app/services/countries.service';
import { Country } from 'src/app/model/country.model';
import { VendorTypeService } from 'src/app/services/vendor-type.service';
import { DatePipe } from '@angular/common';

@Component({
  selector: 'app-vendor-signup',
  templateUrl: './vendor-signup.component.html',
  styleUrls: ['./vendor-signup.component.css']
})
export class VendorSignupComponent implements OnInit {
  paymentgateway:string[];
  vtype:Vendor[];
  signupForm:FormGroup;
  formSubmitted:boolean=false;
  vendor:Vendor;
  pg:string[]=[];
  vendorAlreadyExists:boolean=false;

  states:string[];
  countries:Country[];

  selectedCountry:Country;
  
  todayDate:string;
  endDate:string;
  startDate:string;

  constructor(private userService:UserService,private vendorService:VendorService,private dataPipe:DatePipe,
              private countryService:CountriesService,private vendorTypeService:VendorTypeService) { }

  ngOnInit() {
    this.todayDate=this.dataPipe.transform(new Date(),'yyyy-MM-dd');
    

    this.getCountries();
    this.vendorTypeService.getAllVendorTypes().subscribe((vendorTypeList:Vendor[])=>{
      this.vtype=vendorTypeList;
      
    })

    this.paymentgateway=['GooglePay','PhonePe','PayTm','Debit Card','Amazon Pay'];
    //this.vtype=['DTH','Electricity','Telephone','DTH','Insurance','Tax','Credit Card','Loan account'];
    this.signupForm=new FormGroup({
      'vname': new FormControl(null, [Validators.required,Validators.maxLength(25)]),
      'vcompanyregno': new FormControl(null,[Validators.required,Validators.maxLength(45)]),
      'vvendortype': new FormControl("",Validators.required),
      'pswd':new FormControl(null,Validators.required),
      'cpswd':new FormControl(null,[Validators.required,this.confirmPassword.bind(this)]),
      'vcountry':new FormControl("",Validators.required),
      'vstate':new FormControl("",Validators.required),
      'vaddress':new FormControl(null,Validators.required),
      'vemail':new FormControl(null,[Validators.required,Validators.pattern("[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,}$")]),
      'vcontactnumber':new FormControl(null,[Validators.required,Validators.pattern('^[0-9]+$'),Validators.maxLength(10),Validators.minLength(10)]),
      'vwebsite':new FormControl(null,[Validators.required,Validators.pattern('^(http:\/\/|https:\/\/)?(www.)?([a-zA-Z0-9]+).[a-zA-Z0-9]*.[a-z]{3}.?([a-z]+)?$')]),
      'vcertificateissuedate':new FormControl(null,Validators.required),
      'vcertificatevaliditydate':new FormControl(null,Validators.required),
      'vyearofestablishment':new FormControl(null,[Validators.required,Validators.maxLength(4),Validators.pattern('^[0-9]+$')]),
      'vpaymentgateway':new FormControl(null,Validators.required)
    });
  }

  vendorExist() {
    if(this.signupForm.get('vname').value.length>0){
      
    this.userService.doesUserExist(this.signupForm.get('vname').value).subscribe(data=>{
      this.vendorAlreadyExists=data;
    });
  }}


  confirmPassword(formControl:FormControl){
    if(this.signupForm){
      if(formControl.value && formControl.value.length>0 && formControl.value!==this.signupForm.get('pswd').value){
        return { 'nomatch' : true };
      }
    }
    return null;
  }


  getSelectedPaymentGateway(event:string) {
    if(this.signupForm.get('vpaymentgateway').value)
      this.pg.push(event);
    else{
      const fd = this.pg.findIndex(payg => payg == event);
      this.pg.splice(fd,1);
    }
  }

  getCountries(){
    this.countryService.getCountries().subscribe((countries:Country[])=>{
      this.countries=countries;
      
      
     
      
      
    });
  }

  getStates(){//country){
    let country =this.signupForm.value.vcountry.country;
    this.states=this.signupForm.value.vcountry.states;
   
  }

  onSignUp(){
    this.formSubmitted=true;
    let vendorname=this.signupForm.get('vname').value;
    let companyregno=this.signupForm.get('vcompanyregno').value;
    let vendortype=this.signupForm.get('vvendortype').value;
    let password=this.signupForm.get('pswd').value;
    let address=this.signupForm.get('vaddress').value;
    let country=this.signupForm.get('vcountry').value.country;
    let state=this.signupForm.get('vstate').value;
    let emailaddress=this.signupForm.get('vemail').value;
    let contactnumber=this.signupForm.get('vcontactnumber').value;
    let website=this.signupForm.get('vwebsite').value;
    let certificateissuedate=this.signupForm.get('vcertificateissuedate').value;
    let certificatevaliditydate=this.signupForm.get('vcertificatevaliditydate').value;
    let yearofestablishment=this.signupForm.get('vyearofestablishment').value;
    let paymentgateway=this.pg;
    let flag=0;
    
    this.vendor = {vendorName:vendorname,vendorRegNo:companyregno,vendorType:vendortype,password:password,vendorAddress:address,vendorCountry:country,vendorState:state,
      vendorEmail:emailaddress,vendorContactNo:contactnumber,vendorWebSite:website,vendorCertificationIssueDate:certificateissuedate,vendorCertificationValidityDate:certificatevaliditydate,
      vendorYearOfEstablishment:yearofestablishment,paymentGateway:paymentgateway,status:flag};
    
    this.vendorAlreadyExists=false;
    this.vendorService.createNewVendor(this.vendor).subscribe((data)=>{
        console.log(data);
      },
      (error)=>{
        if(error['error']['message']==='Vendor Already Exist'){
          this.vendorAlreadyExists=true;
        }        
      }
      );
      this.signupForm.reset();
  }

  
  setMinimumDate(date:string){
    let date2=this.signupForm.get('vcertificateissuedate').value;
    this.startDate=date2;
    
  
  }

  resetForm(){
    this.signupForm.reset();
  }

}