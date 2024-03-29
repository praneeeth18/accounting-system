import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { Router, ActivatedRoute } from '@angular/router';
import { Invoice } from '../models/invoice';
import { dateNotInFuture } from '../custom-validators';
import { InvoiceDetailsComponent } from '../invoice-details/invoice-details.component';

@Component({
  selector: 'app-update-invoice',
  templateUrl: './update-invoice.component.html',
  styleUrl: './update-invoice.component.css'
})
export class UpdateInvoiceComponent implements OnInit{

  id!: number;
  invoice1: any;
//  invoice1: Invoice = new Invoice();
  public invoiceForm !: FormGroup;
  constructor(private formBuilder : FormBuilder, private http: HttpClient, private router: Router, private route: ActivatedRoute) {}



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
  }
}
