import { Component, OnInit, Input } from '@angular/core';
import { VendorService } from 'src/app/services/vendor.service';
import { Vendor } from 'src/app/model/vendor.model';
import { VendorTypeService } from 'src/app/services/vendor-type.service';
import { AuthenticateService } from 'src/app/services/authentication.service';
import { BillService } from 'src/app/services/bill.service';
import { PaymentService } from 'src/app/services/payment.service';
import { Bill } from 'src/app/model/bill.model';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { Router } from '@angular/router';

@Component({
  selector: 'app-vendor-list',
  templateUrl: './vendor-list.component.html',
  styleUrls: ['./vendor-list.component.css']
})
export class VendorListComponent implements OnInit {

  @Input() type;

  vendorList: Vendor[];

  username: string = '';

  paymentList: Bill[];

  @Input()
  vendorNotAvailable: boolean;

  vendorsAvailable: Vendor[];
  //vendorListSelected: Vendor[];
  addBillForm: FormGroup;
  newBill: Bill = null;
  vendor: string = '';

  constructor(private vendorService: VendorService, private vendorTypeService: VendorTypeService,
    private router: Router, private authenticateService: AuthenticateService, private paymentService: PaymentService,
    private billService: BillService) { }

  @Input()
  vendorTypeSelected: string;

  ngOnInit() {
    console.log("test : " + this.type);
    console.log("vendorNotAvailable: "+this.vendorNotAvailable)
    
    this.username = this.authenticateService.userAuthenticated.userId;
    this.addBillForm = new FormGroup({
      'userName': new FormControl(null),
      'vendorName': new FormControl(null),
      'vendorType': new FormControl(this.vendorTypeSelected),
      'billAmount': new FormControl("",Validators.required)
    });



   // this.vendorTypeSelected = this.type;//this.vendorTypeService.vendortypeSelected;
    
   /*  this.vendorService.getVendorByVendorType(this.vendorTypeSelected).subscribe((vend: Vendor[]) => {
      this.vendorsAvailable = vend;

      console.log("vendors availbale" + this.vendorsAvailable);

      this.vendorsAvailable.forEach(element => {

        this.fetchBillDetails(element.vendorName);
      })


      if (this.vendorsAvailable.length === 0) {
        this.vendorNotAvailable = true;
      }

    }) */



  }

  fetchBillDetails(vendorSelected: string) {
    //this.vendorService.vendorSelected = vendorSelected;
    console.log("asdf");

    this.paymentService.getPaymentDetailsByVendorAndUserPaid(vendorSelected, this.username).
      subscribe((paymentListRecieved: Bill[]) => {
        this.paymentList = paymentListRecieved;
        console.log(this.paymentList)
        this.vendorsAvailable.forEach(element => {
          if (element.vendorName == this.vendorService.vendorSelected) {
            element.paidBills = this.paymentList;
            if (element.paidBills.length === 0) {

            }
            else {
              element.paidBills.forEach(singlePayment => {
                singlePayment.dateOfPay = new Date(singlePayment.dateOfPay);
                singlePayment.dateOfPayString = singlePayment.dateOfPay.toISOString().slice(0, 10);
              })
            }
          }
        })


      });
  }


  billHistoryEmpty() {
    if (this.paymentList.length == 0) {
      return true;
    }
    else {
      return false;
    }
  }

  paymentTypeSelected: string;

  onPaymentTypeSelected(paymentType: string) {
    console.log(paymentType);

    this.paymentTypeSelected = paymentType


  }

  //vendorTypeSelected:string;
  onVendorSelected(vendor: string) {
    console.log(vendor)
    this.vendorService.vendorSelected = vendor;
    console.log(this.type)
    console.log("this.vendorTypeSelected "+this.vendorTypeSelected)
    this.vendor = this.vendorService.vendorSelected;
    this.vendorService.getVendorByVendorName(this.vendor).subscribe((vendor: Vendor) => {
      console.log(vendor);
      
    })

    this.username = this.authenticateService.userAuthenticated.userId;
    
    this.addBillForm.patchValue({
      userName: this.username,
      vendorName: this.vendor,
      vendorType:this.vendorTypeSelected
    })
  }

  //ADDing Bill

  billAdded: boolean = false
  onAddBill(vendor: string) {
    console.log(this.newBill);

    this.newBill = { billAmount: this.addBillForm.get('billAmount').value, username: this.username, vendorName: this.vendor };

    console.log(this.newBill);

    this.billAdded = true;

    this.billService.addBill(this.newBill).subscribe();

  }

  goToPendingBills(vendor: string) {
    this.vendorService.vendorSelected = vendor;
    let redirectUrl = "/pending-bills";
    this.router.navigate([redirectUrl]);
  }

  onCloseModal() {
    setTimeout(() => {
      this.billAdded = false;
      this.addBillForm.reset();
    }, 500);

  }



}
