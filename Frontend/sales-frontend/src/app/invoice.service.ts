import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Invoice } from './invoice';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class InvoiceService {

  private baseURL = "http://localhost:3000/invoice"

  constructor(private httpClient:HttpClient) { }


  getInvoiceList():Observable<Invoice[]>{
    return this.httpClient.get<Invoice[]>(`${this.baseURL}`);
    }
    createInvoice(invoice:Invoice):Observable<Object>{
    return this.httpClient.post(`${this.baseURL}`, invoice);
    }
    getInvoiceById(id:number):Observable<Invoice>{
    return this.httpClient.get<Invoice>(`${this.baseURL}/${id}`);
    }
    updateInvoice(id:number,invoice:Invoice):Observable<Object>{
    return this.httpClient.put(`${this.baseURL}`,invoice);
    }
    deleteInvoice(id:number):Observable<Object>{
    return this.httpClient.delete(`${this.baseURL}/${id}`);
    }
}
