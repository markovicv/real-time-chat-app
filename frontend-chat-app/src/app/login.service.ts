import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { LoginRequest } from './model/login-request';
import { RegisterRequest } from './model/register-request';

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  constructor(private http:HttpClient) { }


  login(loginReq:LoginRequest):Observable<any>{

  
    return this.http.post<any>("http://localhost:9094/auth/login",loginReq);
  }

  register(registerRequest:RegisterRequest):Observable<any>{
    
    return this.http.post<any>("http://localhost:9094/auth/register",registerRequest);
  }


}
