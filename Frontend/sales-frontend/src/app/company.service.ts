import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Company } from './company';

@Injectable({
  providedIn: 'root'
})
export class CompanyService {

  private baseURL = "http://localhost:3000/signup"

  constructor(private httpClient:HttpClient) { }


  getCompanyList():Observable<Company[]>{
    return this.httpClient.get<Company[]>(`${this.baseURL}`);
    }
    createCompany(company:Company):Observable<Object>{
    return this.httpClient.post(`${this.baseURL}`, company);
    }
    getCompanyById(id:number):Observable<Company>{
    return this.httpClient.get<Company>(`${this.baseURL}/${id}`);
    }
    updateCompany(id:number,company:Company):Observable<Object>{
    return this.httpClient.put(`${this.baseURL}`,company);
    }
    deleteCompany(id:number):Observable<Object>{
    return this.httpClient.delete(`${this.baseURL}/${id}`);
    }
}
