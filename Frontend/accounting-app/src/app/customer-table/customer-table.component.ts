import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormBuilder } from '@angular/forms';
import { Router } from '@angular/router';
import { Customer } from '../models/customer';
import { CustomerService } from '../services/customer.service';

@Component({
  selector: 'app-customer-table',
  templateUrl: './customer-table.component.html',
  styleUrl: './customer-table.component.css'
})
export class CustomerTableComponent implements OnInit {

 
  customer!: Customer[];
  constructor(private formBuilder: FormBuilder, private http: HttpClient, private router: Router, private customerService: CustomerService) { }


  ngOnInit(): void {
   

    this.getCustomerDetails();
  }


  getCustomerDetails() {

    this.customerService.getCustomerList().subscribe(data => {
      this.customer = data;
    });
  }
  customerView(id: number) {
    console.log(id);
    this.router.navigate(['customer-view', id]);
  }
  delete(id: number) {
    this.customerService.deleteCustomer(id).subscribe(data => {
      console.log(data);
      this.getCustomerDetails();
    });
    
  }

  edit(id:number){
     console.log(id);
    this.router.navigate(['edit-customer', id]);
   }

}

