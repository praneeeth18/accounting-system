import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { Router, ActivatedRoute } from '@angular/router';
import { Invoice } from '../models/invoice';
import { dateNotInFuture } from '../custom-validators';
import { InvoiceDetailsComponent } from '../invoice-details/invoice-details.component';
import { AccountsReceivableServiceService } from '../services/accounts-receivable-service.service';

@Component({
  selector: 'app-update-invoice',
  templateUrl: './update-invoice.component.html',
  styleUrl: './update-invoice.component.css'
})
export class UpdateInvoiceComponent implements OnInit{

  id!: number;
  invoice1: any = {};
//  invoice1: Invoice = new Invoice();
  public invoiceForm !: FormGroup;
  constructor(private formBuilder : FormBuilder, private http: HttpClient, private router: Router, private route: ActivatedRoute, private invoiceSerivce: AccountsReceivableServiceService) {}



  ngOnInit(): void {
    this.invoiceForm = this.formBuilder.group({
      customername: ['', Validators.required],
      invoicenumber:['', Validators.required],
      date:[null, [Validators.required, dateNotInFuture()]],
      proddesc: ['', Validators.required],
      quantity: ['', Validators.required],
      price:['', Validators.required],
      status:['', Validators.required]
    });

  /*  this.id = this.route.snapshot.params['id'];
    this.invoice1 = new Invoice();
    this.invoiceService.getInvoiceById(this.id).subscribe(data => {
      this.invoice1 = data;
    });*/
    this.id = this.route.snapshot.params['id'];
    this.invoiceSerivce.getInvoiceByReceivableId(this.id).subscribe({
      next: (response) => {
        this.invoice1 = response;
        this.invoiceForm.patchValue({
          status: this.invoice1.status
        });
      },
      error: (error) => {
        console.error('Error fetching invoice:', error);
      }
    })
  }

  

  invoice() {
  /*  this.invoiceService.updateInvoice(this.invoice1)
    .subscribe(res=>{
      alert("Invoice Updated Successfully");
     
      this.router.navigate(['sales-table'], { skipLocationChange: true });

    }, err=>{
      alert("Something went wrong");
    }
    )*/
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
