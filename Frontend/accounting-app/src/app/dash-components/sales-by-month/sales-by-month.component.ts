import { Component, OnInit } from '@angular/core';
import { Chart, registerables } from 'chart.js';
import { Invoice } from '../../models/invoice';
import { AccountsReceivableServiceService } from '../../services/accounts-receivable-service.service';
Chart.register(...registerables);

@Component({
  selector: 'app-sales-by-month',
  templateUrl: './sales-by-month.component.html',
  styleUrl: './sales-by-month.component.css'
})
export class SalesByMonthComponent implements OnInit {

  chartData: Invoice[] = [];
  monthsData: number[] = [];
  myChart!: Chart;
  
    constructor(private invoiceService: AccountsReceivableServiceService) { }
  
    ngOnInit(): void {

      const companyId = sessionStorage.getItem('companyId');
      if (companyId) {
        // Parse companyId to number
        const companyIdNumber = parseInt(companyId, 10);
        
        // Call the service method with the retrieved companyId
        this.invoiceService.getInvoiceByCompanyId(companyIdNumber).subscribe({
          next: (response) => {
            console.log(response);
            this.chartData = response;// Assign the response data to the invoice property
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
  
    aggregateSalesByMonth(data: Invoice[]): number[] {
      const salesPerMonth: number[] = new Array(12).fill(0);
      data.forEach(invoice => {
        const date = new Date(invoice.dueDate);
        const month = date.getMonth();
        salesPerMonth[month] += (invoice.price)*(invoice.quantity);
      });
      return salesPerMonth;
    }
  
    renderChart(data: number[]): void {
      const months = [
        'January', 'February', 'March', 'April', 'May', 'June',
        'July', 'August', 'September', 'October', 'November', 'December'
      ];
      this.myChart = new Chart("myChart", {
        type: 'bar',
        data: {
          labels: months,
          datasets: [{
            label: 'Sales per Month',
            data: data,
            backgroundColor: 'rgba(206, 17, 162, 0.2)',
            borderColor: 'rgba(206, 17, 162, 1)',
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
