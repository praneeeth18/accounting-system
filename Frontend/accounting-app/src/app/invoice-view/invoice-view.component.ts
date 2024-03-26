import { Component, OnInit } from '@angular/core';
import { Invoice } from '../models/invoice';
import { ActivatedRoute } from '@angular/router';
import { Company } from '../models/company';
import { jsPDF } from 'jspdf';
import 'jspdf-autotable';
import { AccountsReceivableServiceService } from '../services/accounts-receivable-service.service';



@Component({
  selector: 'app-invoice-view',
  templateUrl: './invoice-view.component.html',
  styleUrl: './invoice-view.component.css'
})
export class InvoiceViewComponent implements OnInit{

  id!: number;
  invoice!: any | null;
  company: Company = new Company();

  constructor(private invoiceSerivce: AccountsReceivableServiceService,private route: ActivatedRoute) {}
  
  ngOnInit(): void {
    this.id = this.route.snapshot.params['id'];
    this.invoiceSerivce.getInvoiceByReceivableId(this.id).subscribe({
      next: (response) => {
        this.invoice = response;
      },
      error: (error) => {
        console.error('Error fetching invoice:', error);
      }
    })
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

    // Decrease font size for invoice details
    doc.setFontSize(14);

    // Add invoice details
    let y = 30;
    doc.text(`Invoice Number: ${this.invoice.invoiceNumber}`, 10, y);
    y += 10;
    doc.text(`Date: ${this.invoice.dueDate}`, 10, y);
    y += 10;
    doc.text(`Customer Name: ${this.invoice.customerName}`, 10, y);
    y += 20;

    // Decrease font size for product details heading
    doc.setFontSize(16);
    doc.text('Product Details', 10, y);

    // Add product details table
    let startY = y + 10;
    const tableColumns = ['Product Description', 'Price/Unit', 'Quantity', 'Amount'];
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
