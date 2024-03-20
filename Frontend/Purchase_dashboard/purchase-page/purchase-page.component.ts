import { Component } from '@angular/core';

@Component({
  selector: 'app-purchase-page',
  templateUrl: './purchase-page.component.html',
  styleUrl: './purchase-page.component.css'
})
export class PurchasePageComponent {
  customers: any[] = [
    {
        payableId: 1, invoiceNumber: 'AA001', date: '13/3/2024', customerName: 'John',
        productDescription: 'Laptop', quantity: 1, price: 100000, totalAmount: 100000, status: 'paid'
    },
    {
      payableId: 2, invoiceNumber: 'AA002', date: '13/3/2024', customerName: 'Mike',
      productDescription: 'Mobile', quantity: 2, price: 65000, totalAmount: 130000, status: 'pending'
    },
    {
      payableId: 3, invoiceNumber: 'AA003', date: '1/2/2024', customerName: 'Tom',
      productDescription: 'Furniture', quantity: 1, price: 20000, totalAmount: 20000, status: 'paid'
    },
    {
      payableId: 4, invoiceNumber: 'AA004', date: '10/1/2024', customerName: 'Alex',
      productDescription: 'PC', quantity: 1, price: 80000, totalAmount: 80000, status: 'paid'
    },
  ];
}