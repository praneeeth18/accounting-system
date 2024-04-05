import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { IndexComponent } from './index/index.component';
import { LoginComponent } from './login/login.component';
import { RegisterComponent } from './register/register.component';
import { DashboardComponent } from './dashboard/dashboard.component';
import { InvoiceDetailsComponent } from './invoice-details/invoice-details.component';
import { SalesTableComponent } from './sales-table/sales-table.component';
import { CompanyProfileComponent } from './company-profile/company-profile.component';
import { LedgerEntryComponent } from './ledger-entry/ledger-entry.component';
import { InvoiceViewComponent } from './invoice-view/invoice-view.component';
import { PurchasesComponent } from './purchases/purchases.component';
import { PurchaseTableComponent } from './purchase-table/purchase-table.component';
import { PurchaseInvoiceViewComponent } from './purchase-invoice-view/purchase-invoice-view.component';
import { EditvendorComponent } from './editvendor/editvendor.component';
import { VendorComponent } from './vendor/vendor.component';
import { VendortableComponent } from './vendortable/vendortable.component';
import { EditCustomerComponent } from './edit-customer/edit-customer.component';
import { CustomerViewComponent } from './customer-view/customer-view.component';
import { CustomerDetailsComponent } from './customer-details/customer-details.component';
import { CustomerTableComponent } from './customer-table/customer-table.component';
import { LedgerTableComponent } from './ledger-table/ledger-table.component';
import { UpdateInvoiceComponent } from './update-invoice/update-invoice.component';
import { HomepageComponent } from './homepage/homepage.component';
import { ResetPasswordComponent } from './reset-password/reset-password.component';
import { ForgotPasswordComponent } from './forgot-password/forgot-password.component';
import { UpdatePurchaseInvoiceComponent } from './update-purchase-invoice/update-purchase-invoice.component';
import { PurchaseByMonthComponent } from './dash-components/purchase-by-month/purchase-by-month.component';
import { SalesByMonthComponent } from './dash-components/sales-by-month/sales-by-month.component';
import { TopWidgetsComponent } from './dash-components/top-widgets/top-widgets.component';
import { DashboardChartsComponent } from './dashboard-charts/dashboard-charts.component';

const routes: Routes = [
  {path:'', redirectTo:'', pathMatch:'full'},
  {path:'', component:IndexComponent},
  {path:'login', component:LoginComponent},
  {path:'register', component:RegisterComponent},
  {path:'dashboard', component:DashboardComponent},
  {path:'invoice-details', component:InvoiceDetailsComponent},
  {path:'sales-table', component:SalesTableComponent},
  {path:'profile', component:CompanyProfileComponent},
  {path:'ledgerEntry', component: LedgerEntryComponent},
  {path:'invoice-view/:id', component:InvoiceViewComponent},
  {path:'purchase-entry', component:PurchasesComponent},
  {path:'purchase-table', component:PurchaseTableComponent},
  {path:'purchase-view/:id', component:PurchaseInvoiceViewComponent},
  {path:'vendor', component:VendorComponent},
  {path:'vendortable',component:VendortableComponent},
  {path:'edit/:vendorId',component:EditvendorComponent},
  {path:'edit-customer/:id', component:EditCustomerComponent},
  {path:'customer-view/:id', component:CustomerViewComponent},
  {path:'customer-details', component:CustomerDetailsComponent},
  {path:'customer-table', component:CustomerTableComponent},
  {path:'ledger-entry',component:LedgerEntryComponent},
  {path:'ledger-table',component:LedgerTableComponent},
  {path:'update-invoice/:id', component:UpdateInvoiceComponent},
  {path:'homepage', component:HomepageComponent},
  {path:'reset-password/:email', component:ResetPasswordComponent},
  {path:'forgot-password', component:ForgotPasswordComponent},
  {path:'update-purchase-invoice/:id', component:UpdatePurchaseInvoiceComponent},
  {path:'dashboard-charts', component:DashboardChartsComponent},
  {path:'top-widget', component:TopWidgetsComponent},
  {path:'sales-by-month', component:SalesByMonthComponent},
  {path:'purchase-by-month', component:PurchaseByMonthComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
