import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { CustomerService } from '../services/customer.service';

@Component({
  selector: 'app-customer-details',
  templateUrl: './customer-details.component.html',
  styleUrl: './customer-details.component.css'
})
export class CustomerDetailsComponent implements OnInit{
 
  public customerForm !: FormGroup;
  constructor(private formBuilder : FormBuilder, private http: HttpClient, private router: Router,private customerService:CustomerService) {}
 
  ngOnInit(): void {
    this.customerForm = this.formBuilder.group({
      customerName: ['', Validators.required],
      customerAddress:['', Validators.required],
      customerEmail:['', Validators.required]
     
    })
 
  
  }
 
  customer() {
   
      if (this.customerForm.valid) {
        this.customerService.createCustomer(this.customerForm.value).subscribe(
          response => {
            this.customerForm.reset();
            alert('Customer added successfully');
          },
          error => {
            
            console.error('Error adding customer:', error);
            alert('Failed to add customer. Please try again later.');
          }
        );
      } else {
        
        this.customerForm.markAllAsTouched();
      }
   
  }
}

 

 
