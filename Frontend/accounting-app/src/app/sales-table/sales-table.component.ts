import { Component } from '@angular/core';
import { Invoice } from '../models/invoice';
import { AccountsReceivableServiceService } from '../services/accounts-receivable-service.service';
import { Router } from '@angular/router';



@Component({
  selector: 'app-sales-table',
  templateUrl: './sales-table.component.html',
  styleUrl: './sales-table.component.css'
})
export class SalesTableComponent {

  constructor(private invoiceSerivce: AccountsReceivableServiceService, private router: Router) {}

  invoice!: any;

  itemsPerPage = 5;
  currentPage = 1;

  ngOnInit(){

    
    const companyId = sessionStorage.getItem('companyId');
    if (companyId) {
      // Parse companyId to number
      const companyIdNumber = parseInt(companyId, 10);
      
      // Call the service method with the retrieved companyId
      this.invoiceSerivce.getInvoiceByCompanyId(companyIdNumber).subscribe({
        next: (response) => {
          console.log(response);
          this.invoice = response; // Assign the response data to the invoice property
           
          
        },
        error: (error) => {
          console.error('Error fetching invoices:', error);
        }
      });
    } else {
      alert('Company ID does not exist');
    }
  }

  invoiceView(id:number){
    console.log(id);
    this.router.navigate(['invoice-view', id], { skipLocationChange: true });
  }

  updateInvoice(id:number){
    this.router.navigate(['update-invoice', id], { skipLocationChange: true });
    }

    get paginatedData(){
      const start = (this.currentPage - 1) * (this.itemsPerPage);
      const end = start + this.itemsPerPage;

      return this.invoice.slice(start, end);
    }
  

    changePage(page: number){
      this.currentPage = page;
    }
}
