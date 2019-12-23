import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Vendor } from '../model/vendor.model';
import { AuthenticateService } from './authentication.service';

@Injectable({
  providedIn: 'root'
})
export class VendorService {
  token:string;
  vendorSelected:string='';
  vendorObjectSelected:Vendor;
  constructor(private httpClient: HttpClient,private authService:AuthenticateService) { }

  createNewVendor(vendor:Vendor):Observable<any> {
    return this.httpClient.post<any>("http://localhost:8090/vendor",vendor);
  }

  getVendorByVendorName(vendorName:string){
    this.token=this.authService.accessToken;
    let headers=new HttpHeaders();
    headers=headers.set('Authorization','Bearer '+this.token);
    return this.httpClient.get(`http://localhost:8090/vendor/${vendorName}`,{headers})
  }

  getVendorByVendorType(vendorType:string){
    this.token=this.authService.accessToken;
    let headers=new HttpHeaders();
    headers=headers.set('Authorization','Bearer '+this.token);
    return this.httpClient.get(`http://localhost:8090/vendor/vendorType/${vendorType}`,{headers})
  }

  getVendorByFlag(flag:number){
    this.token=this.authService.accessToken;
    let headers=new HttpHeaders();
    headers=headers.set('Authorization','Bearer '+this.token);
    return this.httpClient.get(`http://localhost:8090/admin/vendor/${flag}`,{headers})
  }

  acceptVendor(vendorName:string){
    this.token=this.authService.accessToken;
    let headers=new HttpHeaders();
    headers=headers.set('Authorization','Bearer '+this.token);
    console.log(this.token);
    return this.httpClient.put<void>(`http://localhost:8090/admin/vendor/accept/${vendorName}`,{headers})    
  }

  declineVendor(vendorName:string){
    this.token=this.authService.accessToken;
    let headers=new HttpHeaders();
    headers=headers.set('Authorization','Bearer '+this.token);
    return this.httpClient.delete<void>(`http://localhost:8090/admin/vendor/rejected/${vendorName}`,{headers})    
  }

  sentBackForCorrection(vendorName:string){
    this.token=this.authService.accessToken;
    let headers=new HttpHeaders();
    headers=headers.set('Authorization','Bearer '+this.token);
    return this.httpClient.put<void>(`http://localhost:8090/admin/vendor/sentBack/${vendorName}`,{headers})    
  }

  updateVendor(vendor:Vendor){
    this.token=this.authService.accessToken;
    let headers=new HttpHeaders();
    headers=headers.set('Authorization','Bearer '+this.token);
    return this.httpClient.put<void>("http://localhost:8090/vendor/editvendor", vendor, { headers });  
  }

}
