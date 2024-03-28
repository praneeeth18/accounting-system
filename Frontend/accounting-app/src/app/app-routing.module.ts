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

const routes: Routes = [
  {path:'', redirectTo:'index', pathMatch:'full'},
  {path:'index', component:IndexComponent},
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
  {path:'customer-table', component:CustomerTableComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
