import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { LoginRequest } from './model/login-request';

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  constructor(private http:HttpClient) { }


  login(loginReq:LoginRequest):Observable<any>{
    return this.http.post<any>("http://localhost:9092/user/login",loginReq);
  }


}
