import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder } from '@angular/forms';
import { Router } from '@angular/router';

@Component({
  selector: 'app-customer-details',
  templateUrl: './customer-details.component.html',
  styleUrl: './customer-details.component.css'
})
export class CustomerDetailsComponent implements OnInit{
 
  public customerForm !: FormGroup;
  constructor(private formBuilder : FormBuilder, private http: HttpClient, private router: Router) {}
 
  ngOnInit(): void {
    this.customerForm = this.formBuilder.group({
      customername: [''],
      address:['' ],
      email:['' ]
     
    })
 
  
  }
 
  customer() {
    this.http.post<any>("http://localhost:3000/customer",this.customerForm.value)
    .subscribe(res=>{
      alert("customer Generated Successfully");
      this.customerForm.reset();
      this.router.navigate(['customer-table']);
 
    }, err=>{
      alert("Something went wrong");
    }
    )
  }
 
}
 
