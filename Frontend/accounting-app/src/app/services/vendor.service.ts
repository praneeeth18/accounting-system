import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Vendor } from '../models/vendor';

@Injectable({
  providedIn: 'root'
})
export class VendorService {

  private baseURL = "http://localhost:8085/details";
  constructor( private httpClient: HttpClient) { }


public httpOptions = {
    headers: new HttpHeaders({
      'Access-Control-Allow-Origin':'*',
      'Authorization':'authkey'
    })
  }
  getVendor(): Observable<Vendor[]>{
    return this.httpClient.get<Vendor[]>(`${this.baseURL}`);
  }

  createVendor(vendor: Vendor): Observable<Object>{
    return this.httpClient.post(`${this.baseURL}`, vendor);
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
}
