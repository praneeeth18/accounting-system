import { Component } from '@angular/core';

declare function sidebar() : any;

@Component({
  selector: 'app-sales-table',
  templateUrl: './sales-table.component.html',
  styleUrl: './sales-table.component.css'
})
export class SalesTableComponent {
  ngOnInit(){

    sidebar();
  }
}
