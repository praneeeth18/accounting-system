import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Purchases } from '../purchases';
import { HttpClient } from '@angular/common/http';
import { PurchasesService } from '../purchases.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-purchases',
  templateUrl: './purchases.component.html',
  styleUrl: './purchases.component.css'
})
export class PurchasesComponent implements OnInit {
  purchaseForm!: FormGroup;
  purchases:Purchases = new Purchases();
  constructor(private fb:FormBuilder , private http: HttpClient,private purchasesService :PurchasesService, private router:Router){}
  ngOnInit(): void {
    this.purchaseForm = this.fb.group({
      invoiceNumber: ['', Validators.required],
      date: ['', Validators.required],
      vendorName: ['', Validators.required],
      description: ['', Validators.required],
      quantity: ['', Validators.required],
      price: ['', Validators.required],
      totalAmount: [''], 
      status: ['', Validators.required],
    });
   
  }

  // createPurchases(){ 
  //   this.purchasesService.createPurchases(this.purchases).subscribe(data=>{ 
      
  //     console.log(data); 
  //   }); 
  //   (error: any)=>console.log(error); }

  onsubmit() {
    if(this.purchaseForm.valid){

      console.log(this.purchaseForm.value);
      this.router.navigate(['purchases-table']);
    }
    else{
      console.log('Invalid');
    }
   //this.createPurchases();
  }
  
} 