import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators ,FormsModule} from '@angular/forms';
import { Router } from '@angular/router';
import { FormDataService } from '../form-data.service';



@Component({
  selector: 'app-ledger-entry',
  templateUrl: './ledger-entry.component.html',
  styleUrls: ['./ledger-entry.component.css']
})
export class LedgerEntryComponent implements OnInit {
  ledgerForm!: FormGroup;
  formData: any;


  constructor(private fb: FormBuilder ,private formDataService: FormDataService,private router :Router) { }

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
    console.log("Form data:", this.ledgerForm.value);
    if (this.ledgerForm.valid) {
      this.formData = this.ledgerForm.value;
      this.formDataService.formData = this.formData;
    
    }
  }
}

