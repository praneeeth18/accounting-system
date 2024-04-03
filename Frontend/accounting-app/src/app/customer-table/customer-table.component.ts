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

 
  customer!: any[];
  constructor(private formBuilder: FormBuilder, private http: HttpClient, private router: Router, private customerService: CustomerService) { }


  ngOnInit(): void {
    const companyId = sessionStorage.getItem('companyId');
      if (companyId) {
        // Parse companyId to number
        const companyIdNumber = parseInt(companyId, 10);
        
        // Call the service method with the retrieved companyId
        this.customerService.getCustomerByCompanyId(companyIdNumber).subscribe({
          next: (response) => {
            console.log(response);
            this.customer = response; // Assign the response data to the invoice property
          },
          error: (error) => {
            console.error('Error fetching invoices:', error);
          }
        });
      } else {
        alert('Company ID does not exist');
      }
    // this.getCustomerDetails();
  }


  // getCustomerDetails() {

  //   this.customerService.getCustomer().subscribe(data => {
  //     this.customer = data;
  //   });
  // }
  customerView(customerId: number) {
    console.log(customerId);
    this.router.navigate(['customer-view', customerId], { skipLocationChange: true });
  }
  deleteCustomer(customerId: number) {
    this.customerService.deleteCustomerById(customerId).subscribe(
      data => {
        console.log("Customer deleted successfully");
        alert("Customer deleted successfully");
        // Optionally, you can remove the deleted vendor from the list
        this.customer = this.customer.filter((vendor: { customerId: number; }) => vendor.customerId !== customerId);
      },
      error => console.log(error)
    );
  }
  edit(customerId:number){
     console.log(customerId);
    this.router.navigate(['edit-customer', customerId], { skipLocationChange: true });
   }

}

