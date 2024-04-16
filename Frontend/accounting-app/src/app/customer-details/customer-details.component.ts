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
      customerEmail:['', Validators.required]
     
    })
 
  
  }
 
  customer() {
   
      if (this.customerForm.valid) {
        const customerDetails = {
          customerName: this.customerForm.value.customerName,
          customerEmail: this.customerForm.value.customerEmail,
          companyId: sessionStorage.getItem('companyId')
        }
        this.customerService.createCustomer(customerDetails).subscribe({
          next: (response) => {
            this.customerForm.reset();
            alert('Customer added successfully');
            this.router.navigate(['/customer-table'], {skipLocationChange:true});
          },
          error: (error) => {
            console.error('Error adding customer:', error);
            alert('Failed to add customer. Please try again later.');
          }
        });
      } else {
        
        this.customerForm.markAllAsTouched();
      }
   
  }
}

 

 
