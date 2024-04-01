import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Customer } from '../models/customer';

@Injectable({
  providedIn: 'root'
})
export class CustomerService {

  private baseURL = "http://localhost:8080/customer"

  constructor(private httpClient:HttpClient) { }


  getCustomer(): Observable<Customer[]>{
    return this.httpClient.get<Customer[]>(`${this.baseURL}`);
  }

  createCustomer(customer: Customer): Observable<Object>{
    return this.httpClient.post(`${this.baseURL}`, customer);
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
}
