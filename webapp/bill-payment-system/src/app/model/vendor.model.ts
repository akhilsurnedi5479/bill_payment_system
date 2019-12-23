import { Bill } from './bill.model';

export interface Vendor{
    vendorId?:number;
    vendorName?:string,
    vendorRegNo?:string,
    vendorType?:string,
    vendorImage?:string,
    password?:string,
    vendorAddress?:string,
    vendorCountry?:string,
    vendorState?:string,
    vendorEmail?:string,
    vendorContactNo?:string,
    vendorWebSite?:string,
    vendorCertificationIssueDate?:Date,
    vendorCertificationValidityDate?:Date,
    vendorYearOfEstablishment?:number,
    paymentGateway?:string[],
    status?:number
    paidBills?:Bill[]
}