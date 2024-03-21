import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { DashboardComponent } from './dashboard/dashboard.component';
import { IndexComponent } from './index/index.component';
import { InvoiceDetailsComponent } from './invoice-details/invoice-details.component';
import { InvoiceViewComponent } from './invoice-view/invoice-view.component';
import { LoginComponent } from './login/login.component';
import { NavbarComponent } from './navbar/navbar.component';
import { RegisterComponent } from './register/register.component';
import { SalesTableComponent } from './sales-table/sales-table.component';
import { CommonModule } from '@angular/common';
import { HttpClientModule } from '@angular/common/http';
import { ReactiveFormsModule, FormsModule } from '@angular/forms';
import { RouterLink } from '@angular/router';
import autoTable from 'jspdf-autotable';

@NgModule({
  declarations: [
    AppComponent,
    NavbarComponent,
    IndexComponent,
    LoginComponent,
    RegisterComponent,
    DashboardComponent,
    InvoiceDetailsComponent,
    SalesTableComponent,
    InvoiceViewComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    ReactiveFormsModule,
    FormsModule,
    CommonModule,
    RouterLink,
    
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
