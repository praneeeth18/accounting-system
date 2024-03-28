import { Component } from '@angular/core';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrl: './navbar.component.css'
})
export class NavbarComponent {

  isLoggedIn(): boolean {
    return sessionStorage.getItem('currentUser') !== null;
  }

  getUsername(): string {
    const currentUser = JSON.parse(sessionStorage.getItem('currentUser') || '{}');
    return currentUser.repFirstName; // Adjust the key based on your user object structure
  }
}
