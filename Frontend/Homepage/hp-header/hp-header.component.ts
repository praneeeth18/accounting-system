import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-hp-header',
  templateUrl: './hp-header.component.html',
  styleUrl: './hp-header.component.css'
})
export class HpHeaderComponent {

  constructor(private router: Router) { }

  ngOnInit(): void { 

  }

  goToDashboard() {
    this.router.navigate(['/dashboard']);
  }
}
