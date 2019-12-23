import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HeaderComponent } from './site/header/header.component';
import { NotFoundComponent } from './site/not-found/not-found.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { LoginComponent } from './customer/login/login.component';
import { SignupComponent } from './customer/signup/signup.component';
import { HttpClientModule } from '@angular/common/http';
import { VendorSignupComponent } from './customer/vendor-signup/vendor-signup.component';
import { VendorApprovalComponent } from './customer/vendor-approval/vendor-approval.component';
import { UserDashboardComponent } from './customer/user-dashboard/user-dashboard.component';
import { ReportIssueComponent } from './report-issue/report-issue.component';
import { VendorEditComponent } from './customer/vendor-edit/vendor-edit.component';
import { HomeComponent } from './home/home.component';
import { FaqComponent } from './customer/faq/faq.component';
import { VendorListComponent } from './customer/vendor-list/vendor-list.component';
import { PaymentGatewayComponent } from './payment-gateway/payment-gateway/payment-gateway.component';
import { PaymentGatewayPaytmComponent } from './payment-gateway/payment-gateway-paytm/payment-gateway-paytm.component';
import { PaymentGatewayGpayComponent } from './payment-gateway/payment-gateway-gpay/payment-gateway-gpay.component';
import { PaymentGatewayDebitComponent } from './payment-gateway/payment-gateway-debit/payment-gateway-debit.component';
import { PaymentGatewayPhonepeComponent } from './payment-gateway/payment-gateway-phonepe/payment-gateway-phonepe.component';
import { PendingBillsComponent } from './customer/pending-bills/pending-bills.component';
import { PaymentHistoryComponent } from './customer/payment-history/payment-history.component';
import { DatePipe } from '@angular/common';
import { UserDetailsComponent } from './site/user-details/user-details.component';
import { AnswerIssueComponent } from './customer/answer-issue/answer-issue.component';

@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    LoginComponent,
    NotFoundComponent,
    HomeComponent,
    SignupComponent,
    VendorSignupComponent,
    VendorApprovalComponent,
    UserDashboardComponent,
    ReportIssueComponent,
    VendorEditComponent,
    FaqComponent,
    VendorListComponent,
    PaymentGatewayComponent,
    PaymentGatewayPaytmComponent,
    PaymentGatewayGpayComponent,
    PaymentGatewayDebitComponent,
    PaymentGatewayPhonepeComponent,
    PendingBillsComponent,
    PaymentHistoryComponent,
    UserDetailsComponent,
    AnswerIssueComponent
  ],
  imports: [
    FormsModule,
    ReactiveFormsModule,
    BrowserModule,
    AppRoutingModule,
    HttpClientModule
  ],
  providers: [DatePipe],
  bootstrap: [AppComponent]
})
export class AppModule { }
