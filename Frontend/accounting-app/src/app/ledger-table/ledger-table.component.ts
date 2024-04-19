import { Component } from '@angular/core';
import { LedgerService } from '../services/ledger.service';

@Component({
  selector: 'app-ledger-table',
  templateUrl: './ledger-table.component.html',
  styleUrl: './ledger-table.component.css'
})
export class LedgerTableComponent {

  ledger:any;

  itemsPerPage = 5;
  currentPage = 1;

  constructor(
    private ledgerService:LedgerService
   ){} 
   ngOnInit(): void {
      this.get();
  }

  get(){
  const companyId = sessionStorage.getItem('companyId');
    if (companyId) {
      // Parse companyId to number
      const companyIdNumber = parseInt(companyId, 10);
      this.ledgerService.getLedgerByCompanyId(companyIdNumber).subscribe({
        next: (response) => {
          this.ledger = response;
        },
        error: (error) => {
          console.log(error);
        }
      });
    }
  }



  get paginatedData(){
    const start = (this.currentPage - 1) * (this.itemsPerPage);
    const end = start + this.itemsPerPage;

    return this.ledger.slice(start, end);
  }


  changePage(page: number){
    this.currentPage = page;
  }
}
