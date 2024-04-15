
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { AccountsReceivableServiceService } from '../services/accounts-receivable-service.service';

import { dateNotInFuture } from '../custom-validators';
import { Customer } from '../models/customer';
import { CustomerService } from '../services/customer.service';



@Component({
  selector: 'app-invoice-details',
  templateUrl: './invoice-details.component.html',
  styleUrl: './invoice-details.component.css'
})
export class InvoiceDetailsComponent implements OnInit{


  public invoiceForm !: FormGroup;

  public customers: Customer[] = [];
  constructor(
    private formBuilder : FormBuilder,
    private router: Router,
  
    private invoiceSerivce: AccountsReceivableServiceService,
    private customerService: CustomerService
  ) {}

  ngOnInit(): void {
    this.invoiceForm = this.formBuilder.group({
      customername: ['', Validators.required],
      date:[null, [Validators.required, dateNotInFuture()]],
      proddesc: ['', Validators.required],
      quantity: ['', Validators.required],
      price:['', Validators.required],
      status:['', Validators.required]
    })
    
    this.loadCustomers();
  }

  loadCustomers(): void {
    const companyId = sessionStorage.getItem('companyId');
    if(companyId) {
      this.customerService.getCustomerByCompanyId(parseInt(companyId)).subscribe({
        next: (response) => {
          this.customers = response;
        },
        error: (error) => {
          console.error('Error fetching customers:', error);
        }
      })
    }
  }

  
  invoice() {
    if(this.invoiceForm.valid) {
     const invoiceDetailsRequest = {
       customerName: this.invoiceForm.value.customername,
       dueDate: this.invoiceForm.value.date,
       productDescription: this.invoiceForm.value.proddesc,
       quantity: this.invoiceForm.value.quantity,
       price: this.invoiceForm.value.price,
       status: this.invoiceForm.value.status,
       companyId: sessionStorage.getItem('companyId')
     };
     this.invoiceSerivce.createInvoice(invoiceDetailsRequest).subscribe({
       next: (response) => {
        if(response === 'Entry created!') { 
          alert('Invoice added sucussfully!');
          console.log(response);
          this.invoiceForm.reset();
          this.router.navigate(['sales-table'], { skipLocationChange: true });
        }
       },
       error: (error) => {
         alert('Unsucessfull!' + error);
         console.error(error);
       }
     })
    } 
  }

}
