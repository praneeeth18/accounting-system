import { Component } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-reset-password',
  templateUrl: './reset-password.component.html',
  styleUrl: './reset-password.component.css'
})
export class ResetPasswordComponent {
  constructor(private router: Router) {}
 
  resetPassword() {
   
    this.router.navigate(['/login']);
  }
 
  goBack() {
   
    this.router.navigate(['/forgot-password']);
  }
}