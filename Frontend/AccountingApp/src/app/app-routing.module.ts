import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LedgerEntryComponent } from './ledger-entry/ledger-entry.component';
import { VendorComponent } from './vendor/vendor.component';
import { VendortableComponent } from './vendortable/vendortable.component';
import { CustomertableComponent } from './customertable/customertable.component';
import { CustomerComponent } from './customer/customer.component';
import { EditvendorComponent } from './editvendor/editvendor.component';



const routes: Routes = [
  { path: '', redirectTo: '/ledger-entry', pathMatch: 'full' },
  { path: 'ledger-entry', component: LedgerEntryComponent },
  { path: 'vendor', component:VendorComponent},
  {path:'vendortable',component:VendortableComponent},
  {path:'customertable',component:CustomertableComponent},
  {path:'customer',component:CustomerComponent},
  {path:'edit/:vendorId',component:EditvendorComponent}
 

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {


 }
