import { Component, OnInit } from '@angular/core';
import { Ledger } from '../models/ledger';
import { LedgerService } from '../services/ledger.service';
import { ActivatedRoute, Router } from '@angular/router';
import { FormBuilder, FormGroup } from '@angular/forms';

@Component({
  selector: 'app-ledger-entry',
  templateUrl: './ledger-entry.component.html',
  styleUrl: './ledger-entry.component.css'
})
export class LedgerEntryComponent implements OnInit{
  constructor(private ledgerService: LedgerService,
    private router: Router,private route:ActivatedRoute) {}

  
  ledger:Ledger=new Ledger();

  ngOnInit(): void {}

  addEntry(){
      this.ledgerService.createledger(this.ledger).subscribe(data=>{
        console.log(data);
        alert("Entry added successfully");
        this.router.navigate(['/ledger-table'], { skipLocationChange: true })
      },
      error=>{
        console.log(error);
        alert("Entry can't be made. Check the details");
      }   
      );
  }

  onSubmit(){
    console.log(this.ledger);
    this.addEntry();
    
  }
}
