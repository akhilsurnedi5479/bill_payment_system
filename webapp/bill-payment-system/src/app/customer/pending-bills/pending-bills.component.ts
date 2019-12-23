import { Component, OnInit, ViewChild } from '@angular/core';
import { Bill } from 'src/app/model/bill.model';
import { BillService } from 'src/app/services/bill.service';
import { AuthenticateService } from 'src/app/services/authentication.service';
import { VendorService } from 'src/app/services/vendor.service';
import { Vendor } from 'src/app/model/vendor.model';
import { PaymentService } from 'src/app/services/payment.service';
import { PaymentGatewayComponent } from 'src/app/payment-gateway/payment-gateway/payment-gateway.component';

@Component({
  selector: 'app-pending-bills',
  templateUrl: './pending-bills.component.html',
  styleUrls: ['./pending-bills.component.css']
})
export class PendingBillsComponent implements OnInit {

  constructor(private billService: BillService, private authenticateService: AuthenticateService,
    private vendorService: VendorService, private paymentService: PaymentService) { }

  username: string;
  vendorname: string;
  pendingBills: Bill[];


  formSubmitted: boolean=false;
  
  vendorNotAvailable: boolean = false;
  vendorSelected: Vendor;
  vendorListSelected: Vendor[];
  pendingBillsEmpty: boolean = false;


  ngOnInit() {
    this.username = this.authenticateService.userAuthenticated.userId
    this.vendorname = this.vendorService.vendorSelected;



    console.log(this.username + " pending bills  " + this.vendorname)
    this.billService.getPendingBillsDetailsByVendorAndUser(this.vendorname, this.username).subscribe((data: Bill[]) => {
      this.pendingBills = data;
      console.log(this.pendingBills)
      if (this.pendingBills.length == 0) {
        this.pendingBillsEmpty = true;
      }

      this.vendorService.getVendorByVendorName(this.vendorname).subscribe((vend: Vendor) => {
        this.vendorSelected = vend;
        console.log(this.vendorSelected)
      })
    });

    this.vendorSelected = this.vendorService.vendorObjectSelected;
  }

  paymentTypeSelected: string;
  paymentToBeDone: Bill;
  
  onPaymentTypeSelected(billId: number, billAmount: number, paymentType: string) {
    console.log(paymentType);

    this.paymentTypeSelected = paymentType
    this.paymentToBeDone = {
      billId: billId,
      billpaymentGateway: this.paymentTypeSelected,
      billAmount: billAmount
    }
    this.paymentService.paymentToBeDone = this.paymentToBeDone;
    //this.paymentGatewayComponent.formSubmitted=false;

    this.formSubmitted = false;
    
    console.log(this.paymentToBeDone)


  }

  onRefreshComponent() {

    this.billService.getPendingBillsDetailsByVendorAndUser(this.vendorname, this.username).subscribe((data: Bill[]) => {
      this.pendingBills = data;
      if (this.pendingBills.length == 0) {
        this.pendingBillsEmpty = true;
      }
      
      console.log( "this.formSubmitted   "+this.formSubmitted)
      this.formSubmitted = true;
      this.vendorNotAvailable = false;
      console.log("this.formSubmitted " + this.formSubmitted)
    });


  }

  



}
