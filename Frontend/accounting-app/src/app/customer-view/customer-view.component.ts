import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Customer } from '../models/customer';
import { CustomerService } from '../services/customer.service';

@Component({
  selector: 'app-customer-view',
  templateUrl: './customer-view.component.html',
  styleUrl: './customer-view.component.css'
})
export class CustomerViewComponent implements OnInit {
 
  id!: number;
  customer: Customer = new Customer();
 
  constructor(private customerService: CustomerService, private route: ActivatedRoute) { }
 
  ngOnInit(): void {
 
    this.id = this.route.snapshot.params['id'];
    this.customer = new Customer();
    this.customerService.getCustomerById(this.id).subscribe(data => {
      this.customer = data;
    });
 
  
 
  }
}