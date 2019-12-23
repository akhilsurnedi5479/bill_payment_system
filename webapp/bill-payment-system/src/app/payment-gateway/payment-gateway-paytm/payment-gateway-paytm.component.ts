import { Component, OnInit, Input } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { AuthenticateService } from 'src/app/services/authentication.service';
import { VendorService } from 'src/app/services/vendor.service';
import { Bill } from 'src/app/model/bill.model';
import { PaymentService } from 'src/app/services/payment.service';

@Component({
  selector: 'app-payment-gateway-paytm',
  templateUrl: './payment-gateway-paytm.component.html',
  styleUrls: ['./payment-gateway-paytm.component.css']
})
export class PaymentGatewayPaytmComponent implements OnInit {

  username:string='';
  vendor:string='';
  spinTheSpinner:boolean=false;
  displayStatus:boolean=false;
  originalAmount:number=this.paymentService.paymentToBeDone.billAmount;
  monthsSelected:number=1;
  amountToPay:number=this.paymentService.paymentToBeDone.billAmount;
  disableOnMinMonths:boolean=true;
  disableOnMaxMonths:boolean=false;
  @Input()
  formSubmitted:boolean;
    paymentDetails:Bill;
  paymentForm:FormGroup;
  paymentmode:string="payTm";

  constructor( private paymentService:PaymentService, private authService:AuthenticateService,private vendorService:VendorService) { }

  ngOnInit() {
    this.vendor=this.vendorService.vendorSelected;
    this.username=this.authService.userAuthenticated.userId;
    this.paymentForm=new FormGroup({
      'amount':new FormControl(),
      'ptmName': new FormControl(this.username,[Validators.required,Validators.pattern('^[a-zA-Z]+$'),Validators.maxLength(45)]),
      'ptmNumber':new FormControl(null,[Validators.required,Validators.pattern('^[0-9]+$'),Validators.maxLength(10)]),
      'ptmPassword':new FormControl(null,[Validators.required,Validators.pattern('^[0-9]+$')]),
    });
    this.vendor = this.vendorService.vendorSelected;
    this.username = this.authService.userAuthenticated.userId;
    this.paymentService.getPaymentDetailsByVendorAndUserAndPaymentGateway(this.vendor, this.username, this.paymentmode)
      .subscribe((previousPayment: Bill) => {
        this.paymentDetails = previousPayment;
        console.log(this.paymentDetails);

        if (this.paymentDetails) {
         this.patchValues();
        }
      });
  }
  patchValues(){  
    this.paymentForm.patchValue({
      apname: this.paymentDetails.nameOnCard,
      apemail: this.paymentDetails.email
    });
}

  increaseMonths(){
    this.disableOnMinMonths=false;
    if(this.monthsSelected===9)
      this.disableOnMaxMonths=true;
    this.monthsSelected++;
    this.amountToPay=this.monthsSelected*this.originalAmount;
  }
  decreaseMonths(){
    this.disableOnMaxMonths=false;
    if(this.monthsSelected===2)
      this.disableOnMinMonths=true;
    this.monthsSelected--;
    this.amountToPay=this.monthsSelected*this.originalAmount;
  }
  onPaid(){
    let id = this.paymentService.paymentToBeDone.billId;
    let username=this.username;
    let vendorSelected=this.vendor;
    let paymentGatgewayName="PayTm";
    let monthsPaid=this.monthsSelected;
    let amountPaid=this.amountToPay;
    let nameOnCard = this.paymentForm.get('ptmName').value;
    let number = this.paymentForm.get('ptmNumber').value;
     this.paymentDetails = {
      billId:id,

      username:username,vendorName:vendorSelected,billpaymentGateway:paymentGatgewayName,
      monthsPaid:monthsPaid, nameOnCard:nameOnCard, email:number,billAmount:amountPaid
    }; 

     console.log(this.paymentDetails);
     this.paymentService.doPayment(this.paymentDetails).subscribe(data=>{
      //this.formSubmitted=false;

    }); 
    this.paymentForm.reset();
    this.patchValues();
    this.formSubmitted=true;

    this.spinTheSpinner=true;
    setTimeout(()=>{
      this.spinTheSpinner=false;
      this.displayStatus=true;

    },1000);
  }

}
