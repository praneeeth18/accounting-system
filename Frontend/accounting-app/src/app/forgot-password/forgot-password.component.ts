import { Component } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-forgot-password',
  templateUrl: './forgot-password.component.html',
  styleUrl: './forgot-password.component.css'
})
export class ForgotPasswordComponent {
  email: string = '';
 
  constructor(private router: Router) {}
 
  submitEmail() {
   
    this.router.navigate(['/reset-password']);
  }
}