import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Invoice } from '../models/invoice';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AccountsReceivableServiceService {

  private baseURL = "http://localhost:8081/accountReceivable/";

  constructor(private httpClient:HttpClient) { }

  createInvoice(invoiceDetails: Invoice): Observable<any> {
    return this.httpClient.post(this.baseURL + 'createAccountReceivable', invoiceDetails);
  }

}
