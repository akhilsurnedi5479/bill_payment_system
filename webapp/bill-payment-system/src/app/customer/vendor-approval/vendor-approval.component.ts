import { Component, OnInit } from '@angular/core';
import { Vendor } from 'src/app/model/vendor.model';
import { VendorService } from 'src/app/services/vendor.service';

@Component({
  selector: 'app-vendor-approval',
  templateUrl: './vendor-approval.component.html',
  styleUrls: ['./vendor-approval.component.css']
})
export class VendorApprovalComponent implements OnInit {

  vendorList: Vendor[];
  vendorsAvailable: Vendor[];
  vendorListsentforcorrection: Vendor[];
  deletedVendor: boolean = false;
  acceptedVendor: boolean = false;
  sentBackForCorrection: boolean = false;
  constructor(private vendorService: VendorService) { }

  ngOnInit() {
    this.vendorService.getVendorByFlag(0).subscribe((vend: Vendor[]) => {
      this.vendorList = vend;
    })
    this.vendorService.getVendorByFlag(2).subscribe((vend: Vendor[]) => {
      this.vendorListsentforcorrection = vend;
    })
    this.vendorService.getVendorByFlag(1).subscribe((vend: Vendor[]) => {
      this.vendorsAvailable = vend;
      console.log(this.vendorsAvailable)
    })
  }

  onApproving(vendorName: string) {
    this.acceptedVendor = true;
    this.deletedVendor = false;
    this.sentBackForCorrection = false;
    this.vendorService.acceptVendor(vendorName).subscribe(() => this.refreshPage());
  }

  onDeclining(vendorName: string) {
    this.acceptedVendor = false;
    this.deletedVendor = true;
    this.sentBackForCorrection = false;
    this.vendorService.declineVendor(vendorName).subscribe(() => this.refreshPage());
  }

  onSendBackForCorrection(vendorName: string) {
    this.acceptedVendor = false;
    this.deletedVendor = false;
    this.sentBackForCorrection = true;
    this.vendorService.sentBackForCorrection(vendorName).subscribe(() => this.refreshPage());
  }

  refreshPage() {
    this.vendorService.getVendorByFlag(0).subscribe((vend: Vendor[]) => {
      this.vendorList = vend;
    })
    this.vendorService.getVendorByFlag(2).subscribe((vend: Vendor[]) => {
      this.acceptedVendor = false;
      this.deletedVendor = false;
      this.sentBackForCorrection = true;
      this.vendorListsentforcorrection = vend;
      setTimeout(() => {
        this.acceptedVendor = false;
        this.deletedVendor = false;
        this.sentBackForCorrection = false;
      }, 1000);
    })
    this.vendorService.getVendorByFlag(1).subscribe((vend: Vendor[]) => {
      this.acceptedVendor = true;
      this.deletedVendor = false;
      this.sentBackForCorrection = false;
      this.vendorsAvailable = vend;
      setTimeout(() => {
        this.acceptedVendor = false;
        this.deletedVendor = false;
        this.sentBackForCorrection = false;
      }, 1000);
    })
  }

  vendorListEmpty(){
    if(this.vendorList==null || this.vendorList.length==0){
      return true;
    }
  }

  vendorListsentforCorrection(){
    if(this.vendorListsentforcorrection==null ||this.vendorListsentforcorrection.length==0){
      return true;
    }
  }

  vendorListAvailable(){
    if(this.vendorsAvailable==null || this.vendorsAvailable.length==0){
      return true;
    }
  }

}
