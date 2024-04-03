import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { Vendor } from '../models/vendor';
import { VendorService } from '../services/vendor.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-vendor',
  templateUrl: './vendor.component.html',
  styleUrl: './vendor.component.css'
})
export class VendorComponent implements OnInit{
  vendorForm!:FormGroup
  vendor: Vendor[] = [];

  constructor(private fb: FormBuilder, private http: HttpClient,private vendorService :VendorService,private router:Router){
  }

  ngOnInit(): void {
    
   this.vendorForm = this.fb.group({
    vendorName: ['', Validators.required],
    vendorEmail: ['', Validators.required]
  });
  }

  onSubmit() {
    if (this.vendorForm.valid) {
      this.vendorService.createVendor(this.vendorForm.value).subscribe(
        response => {
          this.vendorForm.reset();
          alert('Vendor added successfully');
          this.router.navigate(['/vendortable']);
        },
        error => {
          
          console.error('Error adding vendor:', error);
          alert('Failed to add vendor. Please try again later.');
        }
      );
    } else {
      
      this.vendorForm.markAllAsTouched();
    }
  }
}

