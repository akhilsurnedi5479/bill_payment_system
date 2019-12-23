import { Component, OnInit } from '@angular/core';
import { BillService } from 'src/app/services/bill.service';
import { Vendor } from 'src/app/model/vendor.model';
import { VendorTypeService } from 'src/app/services/vendor-type.service';
import { Router } from '@angular/router';
import { VendorService } from 'src/app/services/vendor.service';

@Component({
  selector: 'app-user-dashboard',
  templateUrl: './user-dashboard.component.html',
  styleUrls: ['./user-dashboard.component.css']
})
export class UserDashboardComponent implements OnInit {

  vendorTypeList:Vendor[];

  vendorTypeSelected:string;
  vendorsAvailable:Vendor[];
  constructor(private vendorTypeService:VendorTypeService,private router:Router,private vendorService:VendorService) { }

  ngOnInit() {
      this.vendorTypeService.getAllVendorTypes().subscribe((vendorTypeList:Vendor[])=>{
        this.vendorTypeList=vendorTypeList;
        console.log(this.vendorTypeList);
      })
  }

  showVendorList:boolean=false;

  vendorNotAvailable=false;

  getVendors(vendortype:string){
    this.showVendorList=true;
    this.vendorTypeSelected=vendortype;
    
    this.vendorTypeService.vendortypeSelected=vendortype;
    this.vendorService.getVendorByVendorType(this.vendorTypeSelected).subscribe((vend: Vendor[]) => {
      this.vendorsAvailable = vend;
      this.typeSelected=true;
      this.vendorTypeSelected=vendortype
      
    
  });
  }

  typeSelected:boolean=false;
  selectAType(){
    if(this.typeSelected==true){
      
      return false;
    }else{
      this.typeSelected=true;
      return true;
    }
  }
}
