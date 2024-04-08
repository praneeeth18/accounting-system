import { Component, OnInit } from '@angular/core';
import { Ledger } from '../models/ledger';
import { LedgerService } from '../services/ledger.service';
import { Router } from '@angular/router';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { dateNotInFuture } from '../custom-validators';

@Component({
  selector: 'app-ledger-entry',
  templateUrl: './ledger-entry.component.html',
  styleUrl: './ledger-entry.component.css'
})
export class LedgerEntryComponent implements OnInit{
  
  ledgerForm!: FormGroup;

  constructor(
    private formBuilder: FormBuilder,
    private ledgerService: LedgerService,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.ledgerForm = this.formBuilder.group({
      transactiondate: [null, [Validators.required, dateNotInFuture()]],
      description: ['', Validators.required],
      transactiontype: ['', Validators.required],
      amount: ['', Validators.required]
    });
  }

  onSubmit() {
    if (this.ledgerForm.valid) {
      const ledgerData: Ledger = {
        transactiondate: this.ledgerForm.value.transactiondate,
        description: this.ledgerForm.value.description,
        transactiontype: this.ledgerForm.value.transactiontype,
        amount: this.ledgerForm.value.amount,
        companyId: parseInt(sessionStorage.getItem('companyId') || '0', 10),
        entryid: undefined,
        balance: undefined
      };

      this.ledgerService.createledger(ledgerData).subscribe({
          next: (data) => {
          console.log(data);
          alert('Entry added successfully');
          this.router.navigate(['/ledger-table'], { skipLocationChange: true });
        },
          error: (error) => {
          console.error(error);
          alert("Entry can't be made. Check the details");
        }
      });
      console.log(this.ledgerForm.value);
    }
  }
}
