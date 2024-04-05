import { Component, OnInit } from '@angular/core';
import { AccountsReceivableServiceService } from '../../services/accounts-receivable-service.service';
import { Customer } from '../../models/customer';
import { CustomerService } from '../../services/customer.service';
import { Purchase } from '../../models/purchase';
import { VendorService } from '../../services/vendor.service';

@Component({
  selector: 'app-top-widgets',
  templateUrl: './top-widgets.component.html',
  styleUrl: './top-widgets.component.css'
})
export class TopWidgetsComponent implements OnInit{

  formattedDate!: string;
  formattedTime!: string;
  currentTime: string = '';
  customer!: Customer[];
  purchase!: Purchase[];
  count=0;
  sum=0;


  constructor(private invoiceService: AccountsReceivableServiceService, private customerService: CustomerService, private vendorService: VendorService){}
   
  

  ngOnInit(): void {
    const today = new Date();
    const year = today.getFullYear();
    const month = String(today.getMonth() + 1).padStart(2, '0');
    const day = String(today.getDate()).padStart(2, '0');
    this.formattedDate = `${year}-${month}-${day}`;

    
    this.updateFormattedTime(); // Initial call to update formatted time
    this.updateCurrentTime(); // Initial call to display current time
    setInterval(() => {
      this.updateCurrentTime();
    }, 1000); // Update current time every second



    //Fetching Total Customers
    const companyId = sessionStorage.getItem('companyId');
    if (companyId) {
      // Parse companyId to number
      const companyIdNumber = parseInt(companyId, 10);

      this.customerService.getCustomerByCompanyId(companyIdNumber).subscribe({
        next: (response) => {
          console.log(response);
          this.customer = response; // Assign the response data to the invoice property
          if(this.customer!=null){
            for(let i = 0; i < this.customer.length; i++){
           this.count++;
           }
            this.count;
          }
        },
        error: (error) => {
          console.error('Error fetching invoices:', error);
        }
      });
    } else {
      alert('Company ID does not exist');
    }



    //Fetching Total Vendors
    const companyID = sessionStorage.getItem('companyId');
    if (companyID) {
      // Parse companyId to number
      const companyIdNumber = parseInt(companyID, 10);
    this.vendorService.getVendorByCompanyId(companyIdNumber).subscribe({
      next: (response) => {
        console.log(response);
        this.purchase = response;
        if(this.purchase!=null){
          for(let i = 0; i < this.purchase.length; i++){
         this.sum++;
         }
          this.sum;
        }
      },
      error: (error) => {
        console.error('Error fetching invoices:', error);
      }
    });
  } else {
    alert('Company ID does not exist');
  }
  }
  

    updateFormattedTime() {
      const today = new Date();
      const hours = String(today.getHours()).padStart(2, '0');
      const minutes = String(today.getMinutes()).padStart(2, '0');
      const seconds = String(today.getSeconds()).padStart(2, '0');
      this.formattedTime = `${hours}:${minutes}:${seconds}`;
    }
  
    updateCurrentTime() {
      const now = new Date();
      this.currentTime = now.toLocaleTimeString();
    }

  
}

