import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { LoginService } from '../login.service';
import { LoginRequest } from '../model/login-request';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  username;
  password;

  constructor(private loginService:LoginService,private router:Router) { }

  ngOnInit(): void {
  }


  login(){
    let loginReq =new  LoginRequest();
    loginReq.username = this.username;
    loginReq.password = this.password;
    this.loginService.login(loginReq).subscribe(data=>{
      localStorage.setItem("currentId",data.id);

      this.router.navigateByUrl("/chatApp");
    })


  }

}
