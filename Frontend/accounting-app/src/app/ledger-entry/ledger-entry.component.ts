import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { FormDataService } from '../services/form-data.service';
import { LedgerServiceService } from '../services/ledger-service.service';

@Component({
  selector: 'app-ledger-entry',
  templateUrl: './ledger-entry.component.html',
  styleUrl: './ledger-entry.component.css'
})
export class LedgerEntryComponent implements OnInit {
  ledgerForm!: FormGroup;
  formData: any;


  constructor(private fb: FormBuilder ,private formDataService: FormDataService,private router :Router, private ledgerService: LedgerServiceService) { }

  ngOnInit(): void {
    this.ledgerForm = this.fb.group({
      transactionDate: [null,Validators.required],
      description: ['', Validators.required,Validators.pattern(/[a-zA-Z]{5,10}/)],
      amount: [null, [Validators.required, Validators.pattern(/^[0-9]+$/)]],
      transactionType: ['', Validators.required],
    });
    
    
  }

  onSubmit() {
   // console.log("Form validity:", this.ledgerForm.valid);
   if (this.ledgerForm.valid) {
      console.log("Form data:", this.ledgerForm.value);
      // this.formData = this.ledgerForm.value;
      // this.formDataService.formData = this.formData;
      // this.ledgerService.entryLedger(this.formData)
    
    }
  }

  // onSubmit() {
  //   if (this.ledgerForm.valid) {
  //     const formData = this.ledgerForm.value;
  //     const ledgerEntry = {
  //       transactiondate: formData.transactionDate,
  //       description: formData.description,
  //       amount: formData.amount,
  //       transactiontype: formData.transactionType,
  //       // balance: 0 // You might want to set balance to 0 or calculate it based on backend logic
  //     };
  //     console.log(ledgerEntry);
  
  //   //   this.ledgerService.entryLedger(ledgerEntry).subscribe({
  //   //     next: (response) => {
  //   //       console.log('Ledger entry successful:', response);
  //   //       // Optionally, you can redirect to another page or perform other actions here
  //   //       // this.router.navigate(['/ledger']); // Example redirection to the ledger page
  //   //     },
  //   //     error: (error) => {
  //   //       console.error('Error while adding ledger entry:', error);
  //   //       // Optionally, you can display an error message to the user
  //   //     }
  //   //   });
  //   } 
    
  // }
}
