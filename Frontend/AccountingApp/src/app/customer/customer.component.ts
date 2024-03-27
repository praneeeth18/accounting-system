import { Component, OnInit } from '@angular/core';
import { CustomerService } from '../customer.service';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Customer } from '../customer';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-customer',
  templateUrl: './customer.component.html',
  styleUrl: './customer.component.css'
})
export class CustomerComponent {
  customerForm!:FormGroup
  customer: Customer[] = [];

  constructor( private fb: FormBuilder, private http: HttpClient,private vendorService :CustomerService ){
    
    this.customerForm = this.fb.group({
      customerName: ['', [Validators.required, Validators.pattern('[a-zA-Z]{2,}')]],
      customerEmail: ['', Validators.required],
      customerAddress:['',Validators.required]
    });

  }

  onSubmit() {
    if (this.customerForm.valid) {
      this.vendorService.createCustomer(this.customerForm.value).subscribe(
        response => {
          this.customerForm.reset();
          alert('Customer added successfully');
        },
        error => {
          console.error('Error adding customer:', error);
          alert('Failed to add customer. Please try again later.');
        }
      );
      }
      else{
        this.customerForm.markAllAsTouched();
      }
    }
}
