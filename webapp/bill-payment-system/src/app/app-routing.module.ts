import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { HomeComponent } from './home/home.component';
import { LoginComponent } from './customer/login/login.component';
import { SignupComponent } from './customer/signup/signup.component';
import { VendorSignupComponent } from './customer/vendor-signup/vendor-signup.component';
import { VendorApprovalComponent } from './customer/vendor-approval/vendor-approval.component';
import { ReportIssueComponent } from './report-issue/report-issue.component';
import { VendorEditComponent } from './customer/vendor-edit/vendor-edit.component';
import { UserDashboardComponent } from './customer/user-dashboard/user-dashboard.component';
import { FaqComponent } from './customer/faq/faq.component';
import { VendorListComponent } from './customer/vendor-list/vendor-list.component';
import { PendingBillsComponent } from './customer/pending-bills/pending-bills.component';
import { PaymentHistoryComponent } from './customer/payment-history/payment-history.component';
import { AuthGuardService } from './auth.guard.service';


const routes: Routes = [
  //{path:"",component:HomeComponent},
  {path:"login",component:LoginComponent},
  {path:"signup",component:HomeComponent},
  {path:'vendorsignup',component:VendorSignupComponent},
  {path:'faq',component:FaqComponent},

  {path:'vendor-approval',component:VendorApprovalComponent,canActivate:[AuthGuardService]},
  {path:'vendor-edit',component:VendorEditComponent,canActivate:[AuthGuardService]},
  {path:'report',component:ReportIssueComponent,canActivate:[AuthGuardService]},
  {path:'vendors',component:UserDashboardComponent,canActivate:[AuthGuardService]},
  {path:'vendor-list',component:VendorListComponent,canActivate:[AuthGuardService]},
  {path:'pending-bills',component:PendingBillsComponent,canActivate:[AuthGuardService]},
  {path:'bill-history',component:PaymentHistoryComponent,canActivate:[AuthGuardService]},
  {path:'**',redirectTo:'login',pathMatch:'full'}

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
