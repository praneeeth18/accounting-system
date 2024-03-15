import { HttpClient } from '@angular/common/http';
import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';

declare function sidebar() : any;

@Component({
  selector: 'app-invoice-details',
  templateUrl: './invoice-details.component.html',
  styleUrl: './invoice-details.component.css'
})
export class InvoiceDetailsComponent {

  public invoiceForm !: FormGroup;
  constructor(private formBuilder : FormBuilder, private http: HttpClient, private router: Router) {}

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

    sidebar();
  }

  invoice() {
    this.http.post<any>("http://localhost:3000/invoice",this.invoiceForm.value)
    .subscribe(res=>{
      alert("Invoice Generated Successfully");
      this.invoiceForm.reset();
      this.router.navigate(['sales-table']);

    }, err=>{
      alert("Something went wrong");
    }
    )
  }

}
