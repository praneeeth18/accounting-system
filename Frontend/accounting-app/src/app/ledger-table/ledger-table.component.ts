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
    this.ledgerService.displayledger().subscribe(data=>
      {
        this.ledger=data; 
        console.log(this.ledger);
      });
  }
}
