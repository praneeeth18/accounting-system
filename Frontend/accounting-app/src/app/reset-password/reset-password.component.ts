import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { UserServiceService } from '../services/user-service.service';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { LoginRequest } from '../models/login-request';

@Component({
  selector: 'app-reset-password',
  templateUrl: './reset-password.component.html',
  styleUrl: './reset-password.component.css'
})
export class ResetPasswordComponent implements OnInit{

  public resetForm!: FormGroup;
  public email!: string;
  constructor(private router: Router, private userService: UserServiceService, private formBuilder: FormBuilder, private activatedRoute: ActivatedRoute) {}
  ngOnInit(): void {
    this.email=this.activatedRoute.snapshot.params['email'];
    this.resetForm = this.formBuilder.group({
      newPassword:['', Validators.required]
    })
  }
 
  resetPassword() {
    if(this.resetForm.valid) {
      const newCredentials: LoginRequest = {
        email: this.email,
        password: this.resetForm.value.newPassword
      };
      this.userService.forgotPassword(newCredentials).subscribe({
        next: (response) => {
          alert("Password has been changed sucessfully!");
          this.router.navigate(['/login'], { skipLocationChange: true});
        },
        error: (error) => {
          alert("Password reset unsuccessfull!" + error);
        }
      })
    }
  }
 
  goBack() {
   
    this.router.navigate(['/forgot-password'], { skipLocationChange: true });
  }
}