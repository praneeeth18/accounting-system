import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AccountsPayableServiceService } from '../services/accounts-payable-service.service';

@Component({
  selector: 'app-purchase-table',
  templateUrl: './purchase-table.component.html',
  styleUrl: './purchase-table.component.css'
})
export class PurchaseTableComponent implements OnInit{
  
  purchase!: any;
  itemsPerPage = 5;
  currentPage = 1;

  constructor(private router: Router, private payableService: AccountsPayableServiceService) {}

  ngOnInit() {
    const companyId = sessionStorage.getItem('companyId');
    if (companyId) {
      // Parse companyId to number
      const companyIdNumber = parseInt(companyId, 10);
      
      // Call the service method with the retrieved companyId
      this.payableService.getInvoiceByCompanyId(companyIdNumber).subscribe({
        next: (response) => {
          console.log(response);
          this.purchase = response;
        },
        error: (error) => {
          console.error('Error fetching invoices:', error);
        }
      });
    } else {
      alert('Company ID does not exist');
    }
  }

  purchaseView(id:number) {
    console.log(id);
    this.router.navigate(['purchase-view', id], { skipLocationChange: true });
  }

  updateInvoice(id:number){
    this.router.navigate(['update-purchase-invoice', id], { skipLocationChange: true });
    }


    get paginatedData(){
      const start = (this.currentPage - 1) * (this.itemsPerPage);
      const end = start + this.itemsPerPage;

      return this.purchase.slice(start, end);
    }
  

    changePage(page: number){
      this.currentPage = page;
    }
}
