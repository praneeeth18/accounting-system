import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { NavbarComponent } from './navbar/navbar.component';
import { LoginComponent } from './login/login.component';
import { RegisterComponent } from './register/register.component';
import { IndexComponent } from './index/index.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { DashboardComponent } from './dashboard/dashboard.component';
import { SalesTableComponent } from './sales-table/sales-table.component';
import { InvoiceDetailsComponent } from './invoice-details/invoice-details.component';
import { CompanyProfileComponent } from './company-profile/company-profile.component';
import { LedgerEntryComponent } from './ledger-entry/ledger-entry.component';
import { InvoiceViewComponent } from './invoice-view/invoice-view.component';
import { FooterComponent } from './footer/footer.component';
import { PurchasesComponent } from './purchases/purchases.component';
import { PurchaseTableComponent } from './purchase-table/purchase-table.component';
import { PurchaseInvoiceViewComponent } from './purchase-invoice-view/purchase-invoice-view.component';
import { VendortableComponent } from './vendortable/vendortable.component';
import { VendorComponent } from './vendor/vendor.component';
import { EditvendorComponent } from './editvendor/editvendor.component';

@NgModule({
  declarations: [
    AppComponent,
    NavbarComponent,
    LoginComponent,
    RegisterComponent,
    IndexComponent,
    DashboardComponent,
    SalesTableComponent,
    InvoiceDetailsComponent,
    CompanyProfileComponent,
    LedgerEntryComponent,
    InvoiceViewComponent,
    FooterComponent,
    PurchasesComponent,
    PurchaseTableComponent,
    PurchaseInvoiceViewComponent,
    VendortableComponent,
    VendorComponent,
    EditvendorComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
