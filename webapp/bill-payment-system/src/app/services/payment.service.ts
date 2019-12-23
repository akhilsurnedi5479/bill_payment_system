import { Injectable} from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { AuthenticateService } from './authentication.service';
import { Bill } from '../model/bill.model';

@Injectable({
    providedIn:"root"
})
export class PaymentService{

    paymentToBeDone:Bill;

    paymentDone:boolean;

    token:string='';
    constructor(private httpClient:HttpClient,private authService:AuthenticateService) { }
  
    getPaymentDetailsByVendorAndUserAndPaymentGateway(vendorName:string,username:string,billPaymentGateway:string){
      this.token=this.authService.accessToken;
      let headers=new HttpHeaders();
      headers=headers.set('Authorization','Bearer '+this.token);
      return this.httpClient.get(`http://localhost:8090/payment/${vendorName}/${username}/${billPaymentGateway}`,{headers})
    }
  
    getPaymentDetailsByVendorAndUser(vendorName:string,username:string){
      this.token=this.authService.accessToken;
      let headers=new HttpHeaders();
      headers=headers.set('Authorization','Bearer '+this.token);
      return this.httpClient.get(`http://localhost:8090/payment/${vendorName}/${username}`,{headers})
    }
  
    doPayment(bill:Bill){
      this.token=this.authService.accessToken;
      let headers=new HttpHeaders();
      headers=headers.set('Authorization','Bearer '+this.token);
      return this.httpClient.post<any>(`http://localhost:8090/payment`,bill,{headers});
    }

    

    getPaymentDetailsByVendorAndUserPaid(vendorName:string,username:string){
      this.token=this.authService.accessToken;
      let headers=new HttpHeaders();
      headers=headers.set('Authorization','Bearer '+this.token);
      return this.httpClient.get(`http://localhost:8090/payment/bill/paid/${vendorName}/${username}`,{headers})
    }

    getPaymentDetailsByUserPaid(username:string){
      console.log("this is called");
      
      this.token=this.authService.accessToken;
      let headers=new HttpHeaders();
      headers=headers.set('Authorization','Bearer '+this.token);
      return this.httpClient.get(`http://localhost:8090/payment/bill/paid/${username}`,{headers})
    }
  
}