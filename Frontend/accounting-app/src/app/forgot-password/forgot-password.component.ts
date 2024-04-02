import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { UserServiceService } from '../services/user-service.service';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-forgot-password',
  templateUrl: './forgot-password.component.html',
  styleUrl: './forgot-password.component.css'
})
export class ForgotPasswordComponent implements OnInit{
  
  public emailForm!: FormGroup;
  constructor(private router: Router, private userService: UserServiceService, private formBuilder: FormBuilder) {}
  ngOnInit(): void {
    this.emailForm = this.formBuilder.group({
      email:['', Validators.required] 
    })
  }
 
  submitEmail() {
    if (this.emailForm.valid) {
      const email = this.emailForm.value.email;
      // Fetch user details using the retrieved email
      this.userService.getUserDetails(email).subscribe({
        next: (response) => {
          alert("Email verified! Please reset your password.");
          this.router.navigate(['/reset-password', email], { skipLocationChange: true});
        },
        error: (error) => {
          alert("Email does not exist!");
        }
      });
    } else {
      console.log("Error");
    }
  }
}