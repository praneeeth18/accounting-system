import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { Router } from '@angular/router';
import { Invoice } from '../invoice';
import { InvoiceService } from '../invoice.service';

declare function sidebar() : any;

@Component({
  selector: 'app-sales-table',
  templateUrl: './sales-table.component.html',
  styleUrl: './sales-table.component.css'
})
export class SalesTableComponent implements OnInit{

  

  invoice!:Invoice[];


  constructor(private invoiceService:InvoiceService, private http: HttpClient, private router: Router) {}


  ngOnInit(): void {
  
    sidebar();

    this.getInvoiceDetails();
    
  }


  getInvoiceDetails() {

    this.invoiceService.getInvoiceList().subscribe(data=>{
      this.invoice=data;
      });
      
  }


  invoiceView(id:number){
    console.log(id);
    this.router.navigate(['invoice-view', id]);
    }
    


  
}

