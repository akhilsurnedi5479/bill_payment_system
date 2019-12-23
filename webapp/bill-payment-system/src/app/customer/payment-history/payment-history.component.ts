import { Component, OnInit } from '@angular/core';
import { PaymentService } from 'src/app/services/payment.service';
import { AuthenticateService } from 'src/app/services/authentication.service';
import { Bill } from 'src/app/model/bill.model';
import { BillService } from 'src/app/services/bill.service';

@Component({
  selector: 'app-payment-history',
  templateUrl: './payment-history.component.html',
  styleUrls: ['./payment-history.component.css']
})
export class PaymentHistoryComponent implements OnInit {

  constructor(private paymentService:PaymentService,private authService:AuthenticateService ) { }

  username:string;
  paymentList:Bill[];

  ngOnInit() {
    this.username=this.authService.userAuthenticated.userId;
    this.paymentService.getPaymentDetailsByUserPaid(this.username).
      subscribe((paymentListRecieved: Bill[]) => {
        console.log("asdfasdf"+paymentListRecieved)
        this.paymentList = paymentListRecieved;
        this.tempBill=paymentListRecieved;
        console.log(this.paymentList)
  });
}
tempBill:Bill[];
onSearchTextBasedOnVendors(event : any){
  console.log(event.target.value);
  if(event.target.value!==''){
    //console.log(this.tempBill)
    const result = this.tempBill.filter(filtervendor =>filtervendor.vendor.vendorName.toLowerCase().includes(event.target.value.toLowerCase()));
    
      
    this.paymentList=result?result:[];
    
    
  }
  else{
    this.paymentList=[...this.tempBill];
  }
}




onSearchTextBasedOnVendorType(event : any){
  console.log(event.target.value);
  if(event.target.value!==''){
    //console.log(this.tempBill)
    const result = this.tempBill.filter(filtervendor =>filtervendor.vendor.vendorType.vendorTypeName.toLowerCase().includes(event.target.value.toLowerCase()));
    
      
    this.paymentList=result?result:[];
    
    
  }
  else{
    this.paymentList=[...this.tempBill];
  }
}
}
