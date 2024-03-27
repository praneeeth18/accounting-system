import { Component, OnInit } from '@angular/core';
import { PurchasesService } from '../purchases.service';
import { Purchases } from '../purchases';

@Component({
  selector: 'app-purchasestable',
  templateUrl: './purchasestable.component.html',
  styleUrl: './purchasestable.component.css'
})
export class PurchasestableComponent implements OnInit{
  constructor( private purchasesService :PurchasesService ){}
 
purchasesList!:Purchases[];
  ngOnInit(): void {
    this.getPurchasesDetails();
  }
  getPurchasesDetails() {
    this.purchasesService.getPurchases().subscribe(
      data=>{
        this.purchasesList=data;
        console.log(data);
      },
      (error)=>console.log(error)
    )
  }

}
