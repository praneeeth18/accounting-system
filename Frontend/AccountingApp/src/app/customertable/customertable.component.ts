import { Component, OnInit } from '@angular/core';
import { CustomerService } from '../customer.service';

@Component({
  selector: 'app-custometable',
  templateUrl: './customertable.component.html',
  styleUrl: './customertable.component.css'
})
export class CustomertableComponent implements OnInit{
  constructor( private customerService :CustomerService ){}

  public customerList:any;
  ngOnInit(): void { 
    this.getCustomerDetails();
  }
  getCustomerDetails() {
    this.customerService.getCustomer().subscribe(
      data=>{
        this.customerList=data;
        console.log(data);
      },
      (error)=>console.log(error)
    )
  }
}
