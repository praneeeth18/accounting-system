import { Component } from '@angular/core';

@Component({
  selector: 'app-homepage',
  templateUrl: './homepage.component.html',
  styleUrl: './homepage.component.css'
})
export class HomepageComponent {

  currentUser = JSON.parse(sessionStorage.getItem('currentUser') || '{}');
  companyName = this.currentUser.companyName;
}
