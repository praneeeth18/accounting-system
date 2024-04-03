import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { VendorService } from '../services/vendor.service';

@Component({
  selector: 'app-vendortable',
  templateUrl: './vendortable.component.html',
  styleUrl: './vendortable.component.css'
})
export class VendortableComponent implements OnInit {

  constructor( private vendorService :VendorService ,private router:Router){}

  public vendorList:any;
    ngOnInit(): void {
      const companyId = sessionStorage.getItem('companyId');
      if (companyId) {
        // Parse companyId to number
        const companyIdNumber = parseInt(companyId, 10);
        
        // Call the service method with the retrieved companyId
        this.vendorService.getVendorByCompanyId(companyIdNumber).subscribe({
          next: (response) => {
            console.log(response);
            this.vendorList = response; // Assign the response data to the invoice property
          },
          error: (error) => {
            console.error('Error fetching invoices:', error);
          }
        });
      } else {
        alert('Company ID does not exist');
      }
      // this.getvendorDetails();
    }
  // getvendorDetails() {
      // this.vendorService.getVendor().subscribe(
      //   data=>{
      //     this.vendorList=data;
      //     console.log(data);
      //   },
      //   (error)=>console.log(error)
      // )
  // }
  deleteVendor(vendorId: number) {
    this.vendorService.deleteVendorById(vendorId).subscribe(
      data => {
        console.log("Vendor deleted successfully");
        alert("Vendor deleted successfully");
        // Optionally, you can remove the deleted vendor from the list
        this.vendorList = this.vendorList.filter((vendor: { vendorId: number; }) => vendor.vendorId !== vendorId);
      },
      error => console.log(error)
    );
  }
editVendor(vendorId:number){
  this.router.navigate(['/edit',vendorId], { skipLocationChange: true })
}
  
}
