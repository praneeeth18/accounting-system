import { Component } from '@angular/core';


declare function sidebar() : any;

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrl: './dashboard.component.css'
})
export class DashboardComponent {
  ngOnInit(){

    sidebar();
  }
}
