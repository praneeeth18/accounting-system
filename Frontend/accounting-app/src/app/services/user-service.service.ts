import { Injectable } from '@angular/core';
import { LoginRequest } from '../models/login-request';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { RegistrationRequest } from '../models/registration-request';

const baseURL = 'http://localhost:8765/user-service/user/';

@Injectable({
  providedIn: 'root'
})
export class UserServiceService {

  constructor(private http: HttpClient) { }

  register(register: RegistrationRequest): Observable<any> {
    return this.http.post(baseURL + 'register', register);
  } 

  login(login: LoginRequest): Observable<any> {
    return this.http.post(baseURL + 'login', login);
  }

  getUserDetails(email: string): Observable<any> {
    return this.http.get(baseURL + `getUserDetails/${email}`);
  }

  logout() {
    sessionStorage.clear();
  }

  forgotPassword(newCredentials: LoginRequest): Observable<any> {
    return this.http.put(baseURL + `forgotPassword`, newCredentials);
  }
}
