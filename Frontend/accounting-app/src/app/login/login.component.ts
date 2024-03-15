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

  // login() {

  //   this.http.get<any>("http://localhost:3000/signup")

  //   .subscribe(res=>{
  //     const user = res.find((a:any)=>{
  //       return a.companyemail === this.loginForm.value.companyemail && a.password === this.loginForm.value.password
  //     });

  //     if(user){
  //       alert("Login Success");
  //       this.loginForm.reset();
  //       this.router.navigate(['dashboard']);

  //     }else{
  //       alert("Company Not Found");
  //     }
  //   }, err=>{
  //     alert("Something went wrong");
  //   })
  // }

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
          this.loginForm.reset();
          // this.router.navigate(['dashboard']);
        },
        error: (error) => {
          alert("Login Failed: " + error.message);
        }
      });
    }
    console.log("Login works!");
  }
}
