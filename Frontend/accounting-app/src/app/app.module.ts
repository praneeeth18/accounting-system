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
    LedgerEntryComponent
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
