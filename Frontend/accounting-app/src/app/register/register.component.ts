import { HttpClient } from '@angular/common/http';
import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { UserServiceService } from '../services/user-service.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrl: './register.component.css'
})
export class RegisterComponent {

  public signupForm !: FormGroup;
  constructor(private formBuilder : FormBuilder, private http: HttpClient, private router: Router, private userService: UserServiceService) {}

  ngOnInit(): void {
    this.signupForm = this.formBuilder.group({
      companyname: ['', Validators.required],
      companyemail:['', Validators.required],
      password:['', Validators.required],
      firstname: ['', Validators.required],
      lastname: ['', Validators.required],
      address:['', Validators.required],
      city:['', Validators.required],
      state:['', Validators.required],
      pincode:['', Validators.required],
      country:['', Validators.required]
    })
  }

  // signUp() {
  //   this.http.post<any>("http://localhost:3000/signup",this.signupForm.value)
  //   .subscribe(res=>{
  //     alert("SignUp Successfull");
  //     this.signupForm.reset();
  //     this.router.navigate(['login']);

  //   }, err=>{
  //     alert("Something went wrong");
  //   }
  //   )
  // }
  
  signUp(): void {
    if (this.signupForm.invalid) {
      return; // If the form is invalid, do not proceed with signup
    }

    const registrationRequest = this.signupForm.value;
    this.userService.register(registrationRequest).subscribe(
      response => {
        alert('Sign Up Successful');
        this.signupForm.reset();
        this.router.navigate(['login']);
      },
      error => {
        console.error('Sign Up Failed:', error);
        alert('Sign Up Failed. Please try again.');
      }
    );
  }
}
