import { Component, OnInit } from '@angular/core';
import { UserServiceService } from '../services/user-service.service';



@Component({
  selector: 'app-company-profile',
  templateUrl: './company-profile.component.html',
  styleUrl: './company-profile.component.css'
})
export class CompanyProfileComponent implements OnInit{

  currentUser: any;

  constructor(private userService: UserServiceService) {}

  ngOnInit(): void {

  
    // Retrieve email from sessionStorage
    const email = sessionStorage.getItem('currentUserEmail');
    // Retrieve user details from localStorage
    // this.currentUser = JSON.parse(localStorage.getItem('currentUser') || '{}');
    // You can also fetch user details from the server if needed
    if (email) {
      // Fetch user details using the retrieved email
      this.userService.getUserDetails(email).subscribe((response) => {
        this.currentUser = response;
        console.log(this.currentUser);
      });
    } else {
      console.log('User email not found in sessionStorage');
    }
  }
  

}
