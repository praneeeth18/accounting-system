import { HttpClient } from '@angular/common/http';
import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';

@Component({
  selector: 'app-purchases',
  templateUrl: './purchases.component.html',
  styleUrl: './purchases.component.css'
})
export class PurchasesComponent {
  public purchaseForm !: FormGroup;
  constructor(private formBuilder : FormBuilder, private http: HttpClient, private router: Router) {}

  ngOnInit(): void {
    this.purchaseForm = this.formBuilder.group({
      vendorName: ['', Validators.required],
      invoiceNumber:['', Validators.required],
      date:['', Validators.required],
      proddesc: ['', Validators.required],
      quantity: ['', Validators.required],
      price:['', Validators.required],
      status:['', Validators.required]
    })
  }

  onSubmit() {
    if(this.purchaseForm.valid) {
      console.log(this.purchaseForm.value);
    } else {
      console.log('Invalid');
    }
  }
}
