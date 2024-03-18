import { HttpClient } from '@angular/common/http';
import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { UserServiceService } from '../services/user-service.service';
import { RegistrationRequest } from '../models/registration-request';

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
  
  signUp() {
    if (this.signupForm.valid) {
      const registrationRequest: RegistrationRequest = {
        companyName: this.signupForm.value.companyname,
        email: this.signupForm.value.companyemail,
        password: this.signupForm.value.password,
        repFirstName: this.signupForm.value.firstname,
        repLastName: this.signupForm.value.lastname,
        address: this.signupForm.value.address,
        city: this.signupForm.value.city,
        state: this.signupForm.value.state,
        pincode: this.signupForm.value.pincode,
        country: this.signupForm.value.country
      };
    
      this.userService.register(registrationRequest).subscribe(
        {
          next: (response) => {
            alert('Sign Up Successful');
            console.log(response);
            this.signupForm.reset();
            this.router.navigate(['login']);
          },
          error: (error) => {
            console.error('Sign Up Failed:', error);
            if (error.error && error.error.message) {
              alert('Sign Up Failed: ' + error.error.message);
            } else {
              alert('Sign Up Failed. Something went wrong.');
            }
          }
        }
      );
    }
  }
}
