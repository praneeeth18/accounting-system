import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Customer } from '../models/customer';

@Injectable({
  providedIn: 'root'
})
export class CustomerService {

  private baseURL = "http://localhost:8765/customerinfo/customer"

  constructor(private httpClient:HttpClient) { }


  getCustomer(): Observable<any>{
    return this.httpClient.get<Customer[]>(`${this.baseURL}`);
  }

  createCustomer(customer: any): Observable<any>{
    return this.httpClient.post(`${this.baseURL}`, customer, { responseType: 'text' });
  }
  deleteCustomerById(customerId: number): Observable<Object> {
    return this.httpClient.delete(`${this.baseURL}/${customerId}`);
  }
  getCustomerById(customerId: number): Observable<Customer> {
    return this.httpClient.get<Customer>(`${this.baseURL}/${customerId}`);
  }

  updateCustomer(customer: Customer): Observable<Object> {
    return this.httpClient.put(`${this.baseURL}/${customer.customerId}`, customer);
  }

  getCustomerByCompanyId(companyId: number): Observable<any> {
    return this.httpClient.get(this.baseURL + `/getCustomerByCompanyId/${companyId}`);
  }
}
