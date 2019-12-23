import { Subject } from 'rxjs';
import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { AuthenticateService } from './authentication.service';
import { Bill } from '../model/bill.model';

@Injectable({
    providedIn:'root'
})
export class BillService{
    
    
    token:string='';
    constructor(private httpClient:HttpClient,private authService:AuthenticateService) { }
    
    addBill(bill:Bill){
        this.token=this.authService.accessToken;
        let headers=new HttpHeaders();
        headers=headers.set('Authorization','Bearer '+this.token);
        console.log(bill);
        
        return this.httpClient.post<any>(`http://localhost:8090/payment/bill`,bill,{headers});
      }

      getPendingBillsDetailsByVendorAndUser(vendorName:string,username:string){
        this.token=this.authService.accessToken;
        let headers=new HttpHeaders();
        headers=headers.set('Authorization','Bearer '+this.token);
        return this.httpClient.get(`http://localhost:8090/payment/bill/${vendorName}/${username}`,{headers})
      }
}