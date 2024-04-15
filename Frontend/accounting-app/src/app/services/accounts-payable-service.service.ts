import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AccountsPayableServiceService {

  private baseURL = "http://localhost:8765/accounts-payable/accountPayable/";

  constructor(private httpClient:HttpClient) { }

  createPayableInvoice(payableDetails: any): Observable<any> {
    return this.httpClient.post(this.baseURL + `createAccountPayable`, payableDetails, { responseType: 'text' });
  }

  getInvoiceByCompanyId(companyId: number): Observable<any> {
    return this.httpClient.get(this.baseURL + `getEntryByCompanyId/${companyId}`);
  }

  getInvoiceByPayableId(payableId: number): Observable<any> {
    return this.httpClient.get(this.baseURL + `getEntryByPayableId/${payableId}`);
  }

  updateInvoice(payableId: number, payableDetails: any) {
    return this.httpClient.put(this.baseURL + `updatePayable/${payableId}`, payableDetails, { responseType: 'text' });
  }
}
