import { HttpClient } from '@angular/common/http';
import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { AccountsReceivableServiceService } from '../services/accounts-receivable-service.service';
import { Invoice } from '../models/invoice';



@Component({
  selector: 'app-invoice-details',
  templateUrl: './invoice-details.component.html',
  styleUrl: './invoice-details.component.css'
})
export class InvoiceDetailsComponent {

  public invoiceForm !: FormGroup;
  constructor(private formBuilder : FormBuilder, private http: HttpClient, private router: Router, private invoiceSerivce: AccountsReceivableServiceService) {}

  ngOnInit(): void {
    this.invoiceForm = this.formBuilder.group({
      customername: ['', Validators.required],
      invoicenumber:['', Validators.required],
      date:['', Validators.required],
      proddesc: ['', Validators.required],
      quantity: ['', Validators.required],
      price:['', Validators.required],
      status:['', Validators.required]
    })

    
  }

  invoice() {
    if(this.invoiceForm.valid) {
     const invoiceDetailsRequest: Invoice = {
       id: 1,
       invoiceNumber: this.invoiceForm.value.invoicenumber,
       customerName: this.invoiceForm.value.customername,
       dueDate: this.invoiceForm.value.date,
       productDescription: this.invoiceForm.value.proddesc,
       quantity: this.invoiceForm.value.quantity,
       price: this.invoiceForm.value.price,
       status: this.invoiceForm.value.status,
       companyId: 1
     };
     this.invoiceSerivce.createInvoice(invoiceDetailsRequest).subscribe({
       next: (response) => {
         alert('Invoice added sucussfully!');
         console.log(response);
         this.invoiceForm.reset();
       },
       error: (error) => {
         console.error(error);
       }
     })
    } 
   }

}
