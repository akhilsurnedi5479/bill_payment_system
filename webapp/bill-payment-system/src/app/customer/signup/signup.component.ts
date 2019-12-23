import { Component, OnInit, Input } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { UserService } from 'src/app/services/user.service';
import { AuthenticateService } from 'src/app/services/authentication.service';
import { User } from 'src/app/model/user.model';

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.css']
})
export class SignupComponent implements OnInit {
  signupForm:FormGroup;
  selectSignUp:FormGroup;
  formSubmitted:boolean=false;
  admin:boolean=false;
  user:User;
  userAlreadyExists:boolean=false;

  @Input() data;


  admin_color:string ="grey";
  ven_color="grey";
  cust_color="red";
  isAdmin:boolean=false;
  isCust:boolean=true;
  isVen:boolean=false;
  

  constructor(private userService:UserService,private authService:AuthenticateService) { }

  ngOnInit() {
    console.log(this.data);
    
    this.signupForm=new FormGroup({
      'uname': new FormControl(null, [Validators.required,Validators.maxLength(25)]),
      'fname': new FormControl(null,[Validators.required,Validators.pattern('^[a-zA-Z]+$'),Validators.maxLength(45)]),
      'lname': new FormControl(null,[Validators.required,Validators.pattern('^[a-zA-Z]+$'),Validators.maxLength(45)]),
      'pswd':new FormControl(null,Validators.required),
      'cpswd':new FormControl(null,[Validators.required,this.confirmPassword.bind(this)]),
      'uage':new FormControl(null,[Validators.required,Validators.pattern('^[0-9]+$'),Validators.min(18)]),
      'ugender':new FormControl("Male",Validators.required),
      'ucontactnumber':new FormControl(null,[Validators.required,Validators.pattern('^[0-9]+$'),Validators.maxLength(10)]),
      'uaadharnumber':new FormControl(null,[Validators.required,Validators.pattern('^[0-9]+$'),Validators.maxLength(12)]),
      'upan':new FormControl(null,[Validators.required,Validators.pattern('^[a-zA-Z0-9]+$'),Validators.maxLength(12)]),
      'isAdminChecked':new FormControl(null)
    });
    this.selectSignUp=new FormGroup({
     'selectSignUpRole':new FormControl("Customer")
    });
  }

  userExist() {
    if(this.signupForm.get('uname').value.length>0){
    
    
    this.userService.doesUserExist(this.signupForm.get('uname').value).subscribe(data=>{
      this.userAlreadyExists=data;
    });
  }}

  getIsAdminUser(){
    this.admin=this.signupForm.get('isAdminChecked').value;
    console.log(this.admin);
  }

  confirmPassword(formControl:FormControl){
    if(this.signupForm){
      if(formControl.value && formControl.value.length>0 && formControl.value!==this.signupForm.get('pswd').value){
        return { 'nomatch' : true };
      }
    }
    return null;
  }

  onSignUp(){
    this.formSubmitted=true;
    let username=this.signupForm.get('uname').value;
    let firstname=this.signupForm.get('fname').value;
    let lastname=this.signupForm.get('lname').value;
    let password=this.signupForm.get('pswd').value;
    let age=this.signupForm.get('uage').value;
    let gender=this.signupForm.get('ugender').value;
    let contactnumber=this.signupForm.get('ucontactnumber').value;
    let aadharnumber=this.signupForm.get('uaadharnumber').value;
    let pan=this.signupForm.get('upan').value;
    let admin=this.data;
    this.user = {userId:username,firstName:firstname,lastName:lastname,password:password,age:age,gender:gender,
                        contactNumber:contactnumber,aadharNumber:aadharnumber,panNumber:pan,admin:admin};
    this.userAlreadyExists=false;
    console.log(this.user);
    this.userService.signupNewUser(this.user).subscribe((data)=>{
        console.log(data);
      },
      (error)=>{
        if(error['error']['message']==='User Already Exist'){
          this.userAlreadyExists=true;
        }        
      }
      );
      this.signupForm.reset();
  }



  adminSignUp(){
    this.isAdmin=true;
    this.isCust=false;
    this.isVen=false;
    this.admin_color="red";
    this.cust_color="grey";
    this.ven_color="grey";
  }

  custSignUp(){
    this.isAdmin=false;
    this.isCust=true;
    this.isVen=false;
    this.admin_color="grey";
    this.cust_color="red";
    this.ven_color="grey";
  }
  venSignUp(){
    this.isAdmin=false;
    this.isCust=false;
    this.isVen=true;
    this.admin_color="grey";
    this.cust_color="grey";
    this.ven_color="red";
  }
}
