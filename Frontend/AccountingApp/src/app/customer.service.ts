import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Customer } from './customer';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class CustomerService {
  private baseURL = "http://localhost:8080/customer";
  constructor(private httpClient: HttpClient) { }


  public httpOptions = {
    headers: new HttpHeaders({
      'Access-Control-Allow-Origin':'*',
      'Authorization':'authkey'
    })
  }
  getCustomer(): Observable<Customer[]>{
    return this.httpClient.get<Customer[]>(`${this.baseURL}`);
  }

  createCustomer(customer: Customer): Observable<Object>{
    return this.httpClient.post(`${this.baseURL}`, customer);
  }
}
