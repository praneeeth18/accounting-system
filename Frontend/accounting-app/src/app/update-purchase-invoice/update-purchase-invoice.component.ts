import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { dateNotInFuture } from '../custom-validators';
import { AccountsPayableServiceService } from '../services/accounts-payable-service.service';

@Component({
  selector: 'app-update-purchase-invoice',
  templateUrl: './update-purchase-invoice.component.html',
  styleUrl: './update-purchase-invoice.component.css'
})
export class UpdatePurchaseInvoiceComponent implements OnInit{

  id!: number;
  purchase!: any | null;
  public purchaseForm !: FormGroup;
  constructor(private formBuilder : FormBuilder, private http: HttpClient, private route: ActivatedRoute, private router: Router, private payableService: AccountsPayableServiceService) {}

  ngOnInit(): void {
    this.purchaseForm = this.formBuilder.group({
      vendorName: ['', Validators.required],
      invoiceNumber:['', Validators.required],
      date:[null, [Validators.required, dateNotInFuture()]],
      proddesc: ['', Validators.required],
      quantity: ['', Validators.required],
      price:['', Validators.required],
      status:['', Validators.required]
    });

    this.id = this.route.snapshot.params['id'];
    this.payableService.getInvoiceByPayableId(this.id).subscribe({
      next: (response) => {
        this.purchase = response;
        this.purchaseForm.patchValue({
          status: this.purchase.status
        });
      },
      error: (error) => {
        console.error('Error fetching invoice:', error);
      }
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
      this.payableService.updateInvoice(this.id, purchaseRequest).subscribe({
        next: (response) => {
          alert('Invoice Updated sucussfully!');
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
