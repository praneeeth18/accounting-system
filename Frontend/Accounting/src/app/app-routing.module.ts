import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { PurchasestableComponent } from './purchasestable/purchasestable.component';
import { PurchasesComponent } from './purchases/purchases.component';


const routes: Routes = [
  {path:'purchases-table', component:PurchasestableComponent},
  {path:'purchases', component:PurchasesComponent}
    
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
