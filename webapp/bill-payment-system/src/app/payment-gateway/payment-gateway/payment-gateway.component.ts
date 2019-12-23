import { Component, OnInit, Input, Output, EventEmitter, OnChanges } from '@angular/core';
import { AuthenticateService } from 'src/app/services/authentication.service';
import { VendorService } from 'src/app/services/vendor.service';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { PaymentService } from 'src/app/services/payment.service';
import { Bill } from 'src/app/model/bill.model';

@Component({
  selector: 'app-payment-gateway',
  templateUrl: './payment-gateway.component.html',
  styleUrls: ['./payment-gateway.component.css']
})
export class PaymentGatewayComponent implements OnInit,OnChanges {

  username: string = '';
  vendor: string = '';
  spinTheSpinner: boolean = false;
  displayStatus: boolean = false;


  monthsSelected: number = 1;
  amountToPay: number = this.paymentService.paymentToBeDone.billAmount;

  @Input()
  formSubmitted: boolean;



  @Output() formSubmittedInformation: EventEmitter<number> = new EventEmitter<number>();


  formSubmitted1: boolean = this.formSubmitted;

  paymentDetails: Bill;
  paymentForm: FormGroup;
  paymentmode: string = "AmazonPay";

  constructor(private paymentService: PaymentService, private authService: AuthenticateService, private vendorService: VendorService) { }

  ngOnInit() {
    this.vendor = this.vendorService.vendorSelected;
    this.username = this.authService.userAuthenticated.userId;
    this.paymentForm = new FormGroup({
      'amount': new FormControl(),
      'apname': new FormControl(this.username, [Validators.required, Validators.pattern('^[a-zA-Z]+$'), Validators.maxLength(45)]),
      'apemail': new FormControl(null, [Validators.required, Validators.pattern("[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,}$")]),
      'appassword': new FormControl(null, [Validators.required, Validators.pattern('^[0-9]+$')]),
    });
    this.vendor = this.vendorService.vendorSelected;
    this.username = this.authService.userAuthenticated.userId;
    this.paymentService.getPaymentDetailsByVendorAndUserAndPaymentGateway(this.vendor, this.username, this.paymentmode).
      subscribe((previousPayment: Bill) => {
        this.paymentDetails = previousPayment;
        console.log(this.paymentDetails);
        if (this.paymentDetails) {
          this.patchValues();
        }
      });


  }


  patchValues() {
    this.paymentForm.patchValue({
      apname: this.paymentDetails.nameOnCard,
      apemail: this.paymentDetails.email
    });
  }



  onPaid() {
    console.log(this.paymentService.paymentToBeDone)
    let id = this.paymentService.paymentToBeDone.billId;
    let username = this.username;
    let vendorSelected = this.vendor;
    let paymentGatgewayName = "AmazonPay";
    let monthsPaid = this.monthsSelected;
    let amountPaid = this.amountToPay;
    let nameOnCard = this.paymentForm.get('apname').value;
    let email = this.paymentForm.get('apemail').value;
    this.paymentDetails = {
      billId: id,
      username: username, vendorName: vendorSelected, billpaymentGateway: paymentGatgewayName,
      monthsPaid: monthsPaid, nameOnCard: nameOnCard, email: email, billAmount: amountPaid
    };

    console.log(this.paymentDetails);
    this.paymentService.doPayment(this.paymentDetails).subscribe(data => {

    });
    console.log("this.formSubmitted in child" + this.formSubmitted1);

    this.formSubmitted1 = !this.formSubmitted;
    this.paymentForm.reset();
    this.patchValues();

    this.formSubmitted1=true;


    console.log("this.formSubmitted in child" + this.formSubmitted1);


    this.spinTheSpinner = true;
    setTimeout(() => {
      this.spinTheSpinner = false;
      this.displayStatus = true;

    }, 1000);
    this.formSubmittedInformation.emit(1);

  }

  ngOnChanges(){
    this.formSubmitted1=false;
  }




}
