import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class LedgerServiceService {

  private baseUrl = 'http://localhost:8080';

  constructor(private http: HttpClient) { }

  getAllLedger(): Observable<any> {
    return this.http.get(`${this.baseUrl}/ledger`);
  }

  getPrevBalance(): Observable<number> {
    return this.http.get<number>(`${this.baseUrl}/prevbalance`);
  }

  entryLedger(ledger: any): Observable<any> {
    return this.http.post(`${this.baseUrl}/ledger`, ledger);
  }
}
