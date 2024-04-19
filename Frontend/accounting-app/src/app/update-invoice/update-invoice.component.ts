import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { Router, ActivatedRoute } from '@angular/router';
import { Invoice } from '../models/invoice';
import { dateNotInFuture } from '../custom-validators';
import { InvoiceDetailsComponent } from '../invoice-details/invoice-details.component';
import { AccountsReceivableServiceService } from '../services/accounts-receivable-service.service';
import { Customer } from '../models/customer';
import { CustomerService } from '../services/customer.service';

@Component({
  selector: 'app-update-invoice',
  templateUrl: './update-invoice.component.html',
  styleUrl: './update-invoice.component.css'
})
export class UpdateInvoiceComponent implements OnInit{

  id!: number;
  invoice1: any = {};
  public invoiceForm !: FormGroup;
  public customers: Customer[] = [];
  constructor(
    private formBuilder : FormBuilder,
    private router: Router,
    private route: ActivatedRoute,
    private invoiceSerivce: AccountsReceivableServiceService,
    private customerService: CustomerService
  ) {}



  ngOnInit(): void {
    this.invoiceForm = this.formBuilder.group({
      customername: ['', Validators.required],
      invoicenumber:['', Validators.required],
      date:[null, Validators.required],
      proddesc: ['', Validators.required],
      quantity: ['', Validators.required],
      price:['', Validators.required],
      status:['', Validators.required]
    });

    this.id = this.route.snapshot.params['id'];
    this.invoiceSerivce.getInvoiceByReceivableId(this.id).subscribe({
      next: (response) => {
        this.invoice1 = response;
        this.invoiceForm.patchValue({
          customername: this.invoice1.customerName,
          status: this.invoice1.status
        });
      },
      error: (error) => {
        console.error('Error fetching invoice:', error);
      }
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
    const invoiceDetailsRequest = {
      invoiceNumber: this.invoiceForm.value.invoicenumber,
      customerName: this.invoiceForm.value.customername,
      dueDate: this.invoiceForm.value.date,
      productDescription: this.invoiceForm.value.proddesc,
      quantity: this.invoiceForm.value.quantity,
      price: this.invoiceForm.value.price,
      status: this.invoiceForm.value.status,
      companyId: sessionStorage.getItem('companyId')
    };
    this.invoiceSerivce.updateInvoice(this.id, invoiceDetailsRequest).subscribe({
      next: (response) => {
        alert('Invoice Updated sucussfully!');
        console.log(response);
        this.invoiceForm.reset();
        this.router.navigate(['sales-table'], { skipLocationChange: true });
      },
      error: (error) => {
        alert('Unsucessfull!' + error);
        console.error(error);
      }
    })
  }
}
