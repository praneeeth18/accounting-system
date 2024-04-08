import { Component, OnInit } from '@angular/core';
import { Chart, registerables } from 'chart.js';
import { Purchase } from '../../models/purchase';
import { AccountsPayableServiceService } from '../../services/accounts-payable-service.service';
Chart.register(...registerables);

@Component({
  selector: 'app-purchase-by-month',
  templateUrl: './purchase-by-month.component.html',
  styleUrl: './purchase-by-month.component.css'
})
export class PurchaseByMonthComponent implements OnInit{


  
  chartData: Purchase[] = [];
  monthsData: number[] = [];
  
    constructor(private purchaseService: AccountsPayableServiceService) { }
  
    ngOnInit(): void {

      const companyId = sessionStorage.getItem('companyId');
      if (companyId) {
        // Parse companyId to number
        const companyIdNumber = parseInt(companyId, 10);
        
        // Call the service method with the retrieved companyId
        this.purchaseService.getInvoiceByCompanyId(companyIdNumber).subscribe({
          next: (response) => {
            console.log(response);
            this.chartData = response;// Assign the response data to the chartData property
            if (this.chartData) {
              this.monthsData = this.aggregateSalesByMonth(this.chartData);
              this.renderChart(this.monthsData);
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
  
    aggregateSalesByMonth(data: Purchase[]): number[] {
      const salesPerMonth: number[] = new Array(12).fill(0);
      data.forEach(purchase => {
        const date = new Date(purchase.dueDate);
        const month = date.getMonth();
        salesPerMonth[month] += (purchase.price)*(purchase.quantity);
      });
      return salesPerMonth;
    }
  
    renderChart(data: number[]): void {
      const months = [
        'January', 'February', 'March', 'April', 'May', 'June',
        'July', 'August', 'September', 'October', 'November', 'December'
      ];
      const myChart = new Chart("myPurchaseChart", {
        type: 'line',
        data: {
          labels: months,
          datasets: [{
            label: 'Purchases per Month',
            data: data,
            backgroundColor: 'rgba(228, 138, 12, 0.2)',
            borderColor: 'rgba(228, 138, 12, 1)',
            borderWidth: 1
          }]
        },
        options: {
          scales: {
            y: {
              beginAtZero: true
            }
          }
        }
      });
    }
  
  }
  



