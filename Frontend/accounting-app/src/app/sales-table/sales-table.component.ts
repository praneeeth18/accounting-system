import { Component } from '@angular/core';
import { Invoice } from '../models/invoice';
import { AccountsReceivableServiceService } from '../services/accounts-receivable-service.service';
import { Router } from '@angular/router';

declare function sidebar() : any;

@Component({
  selector: 'app-sales-table',
  templateUrl: './sales-table.component.html',
  styleUrl: './sales-table.component.css'
})
export class SalesTableComponent {

  constructor(private invoiceSerivce: AccountsReceivableServiceService, private router: Router) {}

  invoice!: any;

  ngOnInit(){

    sidebar();
    this.invoiceSerivce.getInvoiceByCompanyId(1).subscribe({
      next: (response) => {
        console.log(response);
        this.invoice = response; // Assign the response data to the invoice property
      },
      error: (error) => {
        console.error('Error fetching invoices:', error);
      }
    })
  }

  invoiceView(id:number){
    console.log(id);
    this.router.navigate(['invoice-view', id]);
  }
}
