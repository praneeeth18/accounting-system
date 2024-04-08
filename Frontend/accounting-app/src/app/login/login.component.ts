import { HttpClient } from '@angular/common/http';
import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { LoginRequest } from '../models/login-request';
import { UserServiceService } from '../services/user-service.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})
export class LoginComponent {

  imagepath = "assets/images/figma2.jpg";
  public loginForm !: FormGroup;

  constructor(private formBuilder: FormBuilder, private http: HttpClient, private router: Router, private userService: UserServiceService){

  }

  ngOnInit(): void {
    this.loginForm = this.formBuilder.group({
      companyemail:['', Validators.required],
      password:['', Validators.required]
    })
  }

 
  login() {
    if (this.loginForm.valid) {
      const loginRequest: LoginRequest = {
        email: this.loginForm.value.companyemail,
        password: this.loginForm.value.password
      };
  
      this.userService.login(loginRequest).subscribe({
        next: (response) => {
          alert("Login Success");
          console.log(response);

          sessionStorage.setItem('currentUser', JSON.stringify(response.user));
          sessionStorage.setItem('currentUserEmail', response.user.email);
          sessionStorage.setItem('companyId', response.user.companyId);

          this.loginForm.reset();
        
          this.router.navigate(['/homepage'], { skipLocationChange: true });
        },
        error: (error) => {
          if (error.error && error.error.message) {
            alert('Login Failed: ' + error.error.message);
          } else {
            alert('Login failed. Please try again.');
          }
        }
      });
    }
  }
}
