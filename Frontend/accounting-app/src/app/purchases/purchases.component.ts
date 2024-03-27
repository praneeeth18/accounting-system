import { HttpClient } from '@angular/common/http';
import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { AccountsPayableServiceService } from '../services/accounts-payable-service.service';

@Component({
  selector: 'app-purchases',
  templateUrl: './purchases.component.html',
  styleUrl: './purchases.component.css'
})
export class PurchasesComponent {
  public purchaseForm !: FormGroup;
  constructor(private formBuilder : FormBuilder, private http: HttpClient, private router: Router, private payableService: AccountsPayableServiceService) {}

  ngOnInit(): void {
    this.purchaseForm = this.formBuilder.group({
      vendorName: ['', Validators.required],
      invoiceNumber:['', Validators.required],
      date:['', Validators.required],
      proddesc: ['', Validators.required],
      quantity: ['', Validators.required],
      price:['', Validators.required],
      status:['', Validators.required]
    })
  }

  onSubmit() {
    if(this.purchaseForm.valid) {
      const purchaseRequest = {
        invoiceNumber: this.purchaseForm.value.invoiceNumber,
        vendorName: this.purchaseForm.value.vendorName,
        dueDate: this.purchaseForm.value.date,
        productDescription: this.purchaseForm.value.proddesc,
        quantity: this.purchaseForm.value.quantity,
        price: this.purchaseForm.value.price,
        status: this.purchaseForm.value.status,
        companyId: sessionStorage.getItem('companyId')
      };
      this.payableService.createPayableInvoice(purchaseRequest).subscribe({
        next: (response) => {
          alert('Invoice added sucussfully!');
          console.log(response);
          this.purchaseForm.reset();
          this.router.navigate(['purchase-table']);
        },
        error: (error) => {
          alert('Unsucessfull!' + error);
          console.error(error);
        }
      })
    }
  }
}
