import { Component } from '@angular/core';
import { UserServiceService } from '../services/user-service.service';
import { Router } from '@angular/router';


declare function sidebar() : any;

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrl: './dashboard.component.css'
})
export class DashboardComponent {

  constructor(private userService: UserServiceService, private router: Router) {}

  ngOnInit(){

    sidebar();
  }

  logout() {
    this.userService.logout();
    this.router.navigate(['login'], { skipLocationChange: true });
  }
}
