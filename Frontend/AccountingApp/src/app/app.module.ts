import { NgModule } from '@angular/core';
import { BrowserModule, provideClientHydration } from '@angular/platform-browser';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';

import { LedgerEntryComponent } from './ledger-entry/ledger-entry.component';
import { HeaderComponent } from './header/header.component';
import { SidebarComponent } from './sidebar/sidebar.component';

import { VendorComponent } from './vendor/vendor.component';
import { HttpClientModule, provideHttpClient } from '@angular/common/http';
import { VendortableComponent } from './vendortable/vendortable.component';
import { CustomertableComponent } from './customertable/customertable.component';
import { CustomerComponent } from './customer/customer.component';
import { EditvendorComponent } from './editvendor/editvendor.component';


@NgModule({
  declarations: [
    AppComponent,
    LedgerEntryComponent,
    HeaderComponent,
    SidebarComponent,
    VendorComponent,
    VendortableComponent,
    CustomertableComponent,
    CustomerComponent,
    EditvendorComponent,
     
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule
  ],
  providers: [
    provideClientHydration(),
    provideHttpClient(),
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
