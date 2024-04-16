import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Vendor } from '../models/vendor';

@Injectable({
  providedIn: 'root'
})
export class VendorService {

  private baseURL = "http://localhost:8765/vendorinfo/details";

  constructor( private httpClient: HttpClient) { }
  
  getVendor(): Observable<Vendor[]>{
    return this.httpClient.get<Vendor[]>(`${this.baseURL}`);
  }

  createVendor(vendor: any): Observable<any>{
    return this.httpClient.post(`${this.baseURL}`, vendor, { responseType: 'text' });
  }
  deleteVendorById(vendorId: number): Observable<Object> {
    return this.httpClient.delete(`${this.baseURL}/${vendorId}`);
  }
  getVendorById(vendorId: number): Observable<Vendor> {
    return this.httpClient.get<Vendor>(`${this.baseURL}/${vendorId}`);
  }

  updateVendor(vendor: Vendor): Observable<Object> {
    return this.httpClient.put(`${this.baseURL}/${vendor.vendorId}`, vendor);
  }

  getVendorByCompanyId(companyId: number): Observable<any> {
    return this.httpClient.get(this.baseURL + `/getVendorByCompanyId/${companyId}`);
  }
}
