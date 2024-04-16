import { Component, OnInit } from '@angular/core';
import { Invoice } from '../models/invoice';
import { ActivatedRoute } from '@angular/router';
import { Company } from '../models/company';
import { jsPDF } from 'jspdf';
import 'jspdf-autotable';
import { AccountsReceivableServiceService } from '../services/accounts-receivable-service.service';
import { UserServiceService } from '../services/user-service.service';



@Component({
  selector: 'app-invoice-view',
  templateUrl: './invoice-view.component.html',
  styleUrl: './invoice-view.component.css'
})
export class InvoiceViewComponent implements OnInit{

  id!: number;
  invoice!: any | null;
  company: Company = new Company();
  currentUser: any;

  constructor(private invoiceSerivce: AccountsReceivableServiceService,private userService: UserServiceService, private route: ActivatedRoute) {}
  
  ngOnInit(): void {
    this.id = this.route.snapshot.params['id'];
    this.invoiceSerivce.getInvoiceByReceivableId(this.id).subscribe({
      next: (response) => {
        this.invoice = response;
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
    
    doc.text(`Invoice Number: ${this.invoice.invoiceNumber}`, 10, y);
    y += 10;
    doc.text(`Date: ${this.invoice.dueDate}`, 10, y);
   y += 20;

    // Decrease font size for customer details
 doc.setFontSize(14);

 doc.text(`Customer Details`, 10, y);

 doc.setFontSize(12);
 y += 10;
    doc.text(`Customer Name: ${this.invoice.customerName}`, 10, y);
    y += 20;

    // Decrease font size for product details heading
    doc.setFontSize(14);
    doc.text('Product Details', 10, y);

    // Add product details table
    let startY = y + 10;
    const tableColumns = ['Product Name', 'Price/Unit', 'Quantity', 'Amount'];
    const tableRows = [[
      this.invoice.productDescription,
      this.invoice.price,
      this.invoice.quantity,
      this.invoice.quantity * this.invoice.price
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
