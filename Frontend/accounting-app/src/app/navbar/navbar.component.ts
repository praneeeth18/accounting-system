import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { UserServiceService } from '../services/user-service.service';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrl: './navbar.component.css'
})
export class NavbarComponent {

  constructor(private userService: UserServiceService, private router: Router) {}

  isLoggedIn(): boolean {
    return sessionStorage.getItem('currentUser') !== null;
  }

  getUsername(): string {
    const currentUser = JSON.parse(sessionStorage.getItem('currentUser') || '{}');
    return currentUser.repFirstName; // Adjust the key based on your user object structure
  }


  logout() {
    this.userService.logout();
    this.router.navigate(['login'], { skipLocationChange: true });
  }
}
