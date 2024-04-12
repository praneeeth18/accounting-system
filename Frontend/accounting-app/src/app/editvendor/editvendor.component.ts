import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { Vendor } from '../models/vendor';
import { VendorService } from '../services/vendor.service';

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
          vendorName: ['', Validators.required],
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
            this.router.navigate(['vendortable'], { skipLocationChange: true });
       
          }, err=>{
            alert("Something went wrong");
          }
          )
          
      }
  }
  
