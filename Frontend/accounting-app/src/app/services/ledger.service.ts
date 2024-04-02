import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Ledger } from '../models/ledger';

@Injectable({
  providedIn: 'root'
})
export class LedgerService {

  private baseURL = "http://localhost:8081/ledger";
  constructor(private httpClient:HttpClient) { }

  createledger(ledger: Ledger):Observable<Object>{
    return this.httpClient.post(`${this.baseURL}`,ledger);
  }

  displayledger():Observable<Ledger[]>{
    return this.httpClient.get<Ledger[]>(`${this.baseURL}`);
  }
}
