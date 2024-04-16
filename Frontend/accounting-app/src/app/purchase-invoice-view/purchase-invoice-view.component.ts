import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import jsPDF from 'jspdf';
import { AccountsPayableServiceService } from '../services/accounts-payable-service.service';
import { UserServiceService } from '../services/user-service.service';

@Component({
  selector: 'app-purchase-invoice-view',
  templateUrl: './purchase-invoice-view.component.html',
  styleUrl: './purchase-invoice-view.component.css'
})
export class PurchaseInvoiceViewComponent implements OnInit{

  id!: number;
  purchase!: any | null;
  currentUser: any;

  constructor(private route: ActivatedRoute, private userService: UserServiceService, private payableService: AccountsPayableServiceService) {}
  ngOnInit(): void {
    this.id = this.route.snapshot.params['id'];
    this.payableService.getInvoiceByPayableId(this.id).subscribe({
      next: (response) => {
        this.purchase = response;
      },
      error: (error) => {
        console.error('Error fetching invoice:', error);
      }
    });


    
      // Retrieve email from sessionStorage
      const email = sessionStorage.getItem('currentUserEmail');
      // Retrieve user details from localStorage
      // this.currentUser = JSON.parse(localStorage.getItem('currentUser') || '{}');
      // You can also fetch user details from the server if needed
      if (email) {
        // Fetch user details using the retrieved email
        this.userService.getUserDetails(email).subscribe((response) => {
          this.currentUser = response;
          console.log(this.currentUser);
        });
      } else {
        console.log('User email not found in sessionStorage');
      }
  }

  downloadInvoice() {
    const doc = new jsPDF();

    // Add the heading "INVOICE" centered
    // Calculate text width for centering the heading
    const invoiceHeading = 'INVOICE';
    const fontSize = 20; // Font size of the heading
    const textWidth = doc.getStringUnitWidth(invoiceHeading) * fontSize / doc.internal.scaleFactor;
    const x = (doc.internal.pageSize.width - textWidth) / 2;
    doc.text(invoiceHeading, x, 10);

    
 // Decrease font size for company details
 doc.setFontSize(14);
 let y = 30;
 doc.text(`Company Details`, 10, y);

    // Decrease font size for company details
    doc.setFontSize(12);

    // Add invoice details
     y += 10;
    doc.text(`Company Name: ${this.currentUser?.companyName}`, 10, y);
    y += 10;
    doc.text(`Email: ${this.currentUser?.email}`, 10, y);
    y += 10;
    doc.text(`Address: ${this.currentUser?.address},  ${this.currentUser?.city},  ${this.currentUser?.state}-${this.currentUser?.pincode},  ${this.currentUser?.country}`, 10, y);
    y += 20;


   
    // Decrease font size for invoice details
    doc.setFontSize(12);

    // Add invoice details
   
    doc.text(`Invoice Number: ${this.purchase.invoiceNumber}`, 10, y);
    y += 10;
    doc.text(`Date: ${this.purchase.dueDate}`, 10, y);
    y += 20;

       // Decrease font size for customer details
 doc.setFontSize(14);

 doc.text(`Vendor Details`, 10, y);

 doc.setFontSize(12);
 y += 10;
    doc.text(`Vendor Name: ${this.purchase.vendorName}`, 10, y);
    y += 20;

    // Decrease font size for product details heading
    doc.setFontSize(14);
    doc.text('Product Details', 10, y);

    // Add product details table
    let startY = y + 10;
    const tableColumns = ['Product Name', 'Price/Unit', 'Quantity', 'Amount'];
    const tableRows = [[
      this.purchase.productDescription,
      this.purchase.price,
      this.purchase.quantity,
      this.purchase.quantity * this.purchase.price
    ]];

    // Use autoTable function directly from jsPDF instance
    (doc as any).autoTable({
      startY: startY + 10,
      head: [tableColumns],
      body: tableRows
    });


    const authText = "AUTHORIZED SIGNATORY";
   
    const authTextWidth = doc.getStringUnitWidth(authText) * fontSize / doc.internal.scaleFactor;
    const authTextX = doc.internal.pageSize.width - 20 - authTextWidth;
    const authTextY = doc.internal.pageSize.height - 20;
    doc.text(authText, authTextX, authTextY);

    doc.save('invoice.pdf');
  }
}
