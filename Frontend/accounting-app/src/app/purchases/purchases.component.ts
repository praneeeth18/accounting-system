import { HttpClient } from '@angular/common/http';
import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { AccountsPayableServiceService } from '../services/accounts-payable-service.service';
import { dateNotInFuture } from '../custom-validators';
import { Vendor } from '../models/vendor';
import { VendorService } from '../services/vendor.service';

@Component({
  selector: 'app-purchases',
  templateUrl: './purchases.component.html',
  styleUrl: './purchases.component.css'
})
export class PurchasesComponent {
  public purchaseForm !: FormGroup;
  public vendors: Vendor[] = [];
  constructor(
    private formBuilder: FormBuilder,
    private router: Router,
    private payableService: AccountsPayableServiceService,
    private vendorService: VendorService
  ) {}

  ngOnInit(): void {
    this.purchaseForm = this.formBuilder.group({
      vendorName: ['', Validators.required],
      date:[null, [Validators.required, dateNotInFuture()]],
      proddesc: ['', Validators.required],
      quantity: ['', Validators.required],
      price:['', Validators.required],
      status:['', Validators.required]
    })

    this.loadVendors();
  }

  loadVendors(): void {
    const companyId = sessionStorage.getItem('companyId');
    if (companyId) {
      this.vendorService.getVendorByCompanyId(parseInt(companyId))
        .subscribe({
          next: (data: Vendor[]) => {
            this.vendors = data;
          },
          error: (error) => {
            console.error('Error fetching vendors:', error);
          }
        });
    }
  }

  onSubmit() {
    if(this.purchaseForm.valid) {
      const purchaseRequest = {
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
          this.router.navigate(['purchase-table'], { skipLocationChange: true });
        },
        error: (error) => {
          alert('Unsucessfull!' + error);
          console.error(error);
        }
      })
    }
  }
}
