import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Customer } from '../models/customer';

@Injectable({
  providedIn: 'root'
})
export class CustomerService {

  private baseURL = "http://localhost:3000/customer"

  constructor(private httpClient:HttpClient) { }


  getCustomerList():Observable<Customer[]>{
    return this.httpClient.get<Customer[]>(`${this.baseURL}`);
    }
    createCustomer(customer:Customer):Observable<Object>{
    return this.httpClient.post(`${this.baseURL}`, customer);
    }
    getCustomerById(id:number):Observable<Customer>{
    return this.httpClient.get<Customer>(`${this.baseURL}/${id}`);
    }
    updateCustomer(customer:Customer):Observable<Object>{
      return this.httpClient.put(`${this.baseURL}/${customer.id}`,customer);
      }
    deleteCustomer(id:number):Observable<Object>{
    return this.httpClient.delete(`${this.baseURL}/${id}`);
    }
}
