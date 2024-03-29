import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { Customer } from '../models/customer';
import { CustomerService } from '../services/customer.service';

@Component({
  selector: 'app-edit-customer',
  templateUrl: './edit-customer.component.html',
  styleUrl: './edit-customer.component.css'
})
export class EditCustomerComponent implements OnInit {
  public customerForm !: FormGroup;
  id!: number; 
  customer: Customer = new Customer();


  constructor(private formBuilder : FormBuilder, private http: HttpClient,private route: ActivatedRoute, private customerService: CustomerService, private router: Router) { }


  ngOnInit(): void { 

    this.customerForm = this.formBuilder.group({
      customername: ['', Validators.required],
      address:['', Validators.required],
      email:['', Validators.required]
     
    });
    this.id = this.route.snapshot.params['id'];
    this.customer = new Customer();
    this.customerService.getCustomerById(this.id).subscribe(data => {
      this.customer = data;
    });
    

  
} 
update() {
  this.customerService.updateCustomer(this.customer)
  .subscribe(res=>{
    alert("customer Update Successfully");
    this.router.navigate(['customer-table'], { skipLocationChange: true });

  }, err=>{
    alert("Something went wrong");
  }
  )
}
}

