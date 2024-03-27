import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { VendorService } from '../vendor.service';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Vendor } from '../vendor';

@Component({
  selector: 'app-editvendor',
  templateUrl: './editvendor.component.html',
  styleUrl: './editvendor.component.css'
})
export class EditvendorComponent implements OnInit{
vendorForm!:FormGroup;
vendor:Vendor=new Vendor();
vendorId!:number;

  errors: any;
  constructor(private fb:FormBuilder,private route:ActivatedRoute, private vendorService:VendorService, private router:Router){}

    ngOnInit(){

      this.vendorForm = this.fb.group({
        vendorName: ['', [Validators.required, Validators.pattern('[a-zA-Z]{2,}')]],
        vendorEmail: ['', Validators.required]
      });
      this.vendorId=this.route.snapshot.params['vendorId'];
      this.vendor=new Vendor();
    // alert( this.vendorId);
    this.vendorService.getVendorById( this.vendorId).subscribe(data=>{
      console.log(data);
      this.vendor=data;
    });

    }
    updateVendor(){
        this.vendorService.updateVendor(this.vendor) .subscribe(res=>{
          alert("Vendor Updated Successfully");
          this.router.navigate(['vendortable']);
     
        }, err=>{
          alert("Something went wrong");
        }
        )
        
    }
}
