import { Component } from '@angular/core';
import { LedgerService } from '../services/ledger.service';

@Component({
  selector: 'app-ledger-table',
  templateUrl: './ledger-table.component.html',
  styleUrl: './ledger-table.component.css'
})
export class LedgerTableComponent {

  ledger:any;
  constructor(
    private ledgerService:LedgerService
   ){} 
   ngOnInit(): void {
      this.get();
  }

  get(){
  //   this.ledgerService.displayledger().subscribe(data=>
  //     {
  //       this.ledger=data; 
  //       console.log(this.ledger);
  //     });
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
}
