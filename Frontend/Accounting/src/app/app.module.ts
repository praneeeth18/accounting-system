import { NgModule } from '@angular/core';
import { BrowserModule, provideClientHydration } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HeaderComponent } from './header/header.component';
import { SideBarComponent } from './side-bar/side-bar.component';
import { PurchasesComponent } from './purchases/purchases.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { PurchasestableComponent } from './purchasestable/purchasestable.component';
import { HttpClientModule } from '@angular/common/http';
import { RouterLink } from '@angular/router';


@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    SideBarComponent,
    PurchasesComponent,
    PurchasestableComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule,
    RouterLink
  ],
  providers: [
    provideClientHydration()
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
