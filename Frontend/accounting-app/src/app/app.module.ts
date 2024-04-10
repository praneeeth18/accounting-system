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
import { CustomerDetailsComponent } from './customer-details/customer-details.component';
import { CustomerTableComponent } from './customer-table/customer-table.component';
import { CustomerViewComponent } from './customer-view/customer-view.component';
import { EditCustomerComponent } from './edit-customer/edit-customer.component';
import { LedgerTableComponent } from './ledger-table/ledger-table.component';
import { UpdateInvoiceComponent } from './update-invoice/update-invoice.component';
import { HomepageComponent } from './homepage/homepage.component';
import { ForgotPasswordComponent } from './forgot-password/forgot-password.component';
import { ResetPasswordComponent } from './reset-password/reset-password.component';
import { UpdatePurchaseInvoiceComponent } from './update-purchase-invoice/update-purchase-invoice.component';
import { DashboardChartsComponent } from './dashboard-charts/dashboard-charts.component';
import { SalesByMonthComponent } from './dash-components/sales-by-month/sales-by-month.component';
import { PurchaseByMonthComponent } from './dash-components/purchase-by-month/purchase-by-month.component';
import { TopWidgetsComponent } from './dash-components/top-widgets/top-widgets.component';
import { NgxCurrencyDirective } from 'ngx-currency';
import { PaginationComponent } from './pagination/pagination.component';


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
    EditvendorComponent,
    CustomerDetailsComponent,
    CustomerTableComponent,
    CustomerViewComponent,
    EditCustomerComponent,
    LedgerTableComponent,
    UpdateInvoiceComponent,
    HomepageComponent,
    ForgotPasswordComponent,
    ResetPasswordComponent,
    UpdatePurchaseInvoiceComponent,
    DashboardChartsComponent,
    SalesByMonthComponent,
    PurchaseByMonthComponent,
    TopWidgetsComponent,
    PaginationComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule,
    NgxCurrencyDirective

  
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
