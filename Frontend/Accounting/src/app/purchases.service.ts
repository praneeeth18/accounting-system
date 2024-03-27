import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http'
import { Observable } from 'rxjs';
import { Purchases } from './purchases';

@Injectable({
  providedIn: 'root'
})
export class PurchasesService {
  private baseURL = "http://localhost:8080/acc_payable";
  constructor( private httpClient: HttpClient) { }
 
 
public httpOptions = {
    headers: new HttpHeaders({
      'Access-Control-Allow-Origin':'*',
      'Authorization':'authkey'
    })
  }
  getPurchases(): Observable<Purchases[]>{
    return this.httpClient.get<Purchases[]>(`${this.baseURL}`);
  }
 
  createPurchases(purchases: Purchases): Observable<Object>{
    return this.httpClient.post(`${this.baseURL}`, purchases);
  }
 
}
