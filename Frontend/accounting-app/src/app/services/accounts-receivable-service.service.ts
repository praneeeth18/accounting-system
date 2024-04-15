import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Invoice } from '../models/invoice';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AccountsReceivableServiceService {

  private baseURL = "http://localhost:8765/accounts-receivable-service/accountReceivable/";

  constructor(private httpClient:HttpClient) { }

  createInvoice(invoiceDetails: any): Observable<any> {
    return this.httpClient.post(this.baseURL + 'createAccountReceivable', invoiceDetails, { responseType: 'text' });
  }

  getInvoiceByCompanyId(companyId: number): Observable<any> {
    return this.httpClient.get(this.baseURL + `getEntryByCompanyId/${companyId}`);
  }

  getInvoiceByReceivableId(receivableId: number): Observable<any> {
    return this.httpClient.get(this.baseURL + `getEntryByReceivableId/${receivableId}`);
  }

  updateInvoice(receivableId: number, invoiceDetails: any) {
    return this.httpClient.put(this.baseURL + `updateReceivable/${receivableId}`, invoiceDetails, { responseType: 'text' });
  }
}
