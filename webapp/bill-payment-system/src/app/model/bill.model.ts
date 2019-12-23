export interface Bill{
    billId?:number;
    username?:string,
    vendorName?:string,
    billpaymentGateway?:string,
    monthsPaid?:number,
    nameOnCard?:string,
    cardNumber?:number,
    expirationMonth?:string,
    expirationYear?:number,
    postalCode?:number,
    billAmount?:number,
    email?:string,
    mobileNumber?:string,
    dateOfPay?:Date,
    dateOfPayString?:string
    vendor?:{vendorName:string,
            vendorType:{
                vendorTypeName:string;
            }
        }
}