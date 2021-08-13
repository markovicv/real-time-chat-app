import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { LoginService } from '../login.service';
import { LoginRequest } from '../model/login-request';
import { RegisterRequest } from '../model/register-request';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  username;
  password;
  usernameReg;
  passwordReg


  constructor(private loginService:LoginService,private router:Router) { }

  ngOnInit(): void {
  }


  login(){
    let loginReq =new  LoginRequest();
    loginReq.username = this.username;
    loginReq.password = this.password;
    this.loginService.login(loginReq).subscribe(data=>{
      localStorage.setItem("currentId",data.id);
      localStorage.setItem("jwt",data.jwt);
      localStorage.setItem("username",data.username);

      this.router.navigateByUrl("/chatApp");
    })

  }

  register(){
    let registerRequest = new RegisterRequest();
    registerRequest.username = this.usernameReg;
    registerRequest.password = this.passwordReg;

    this.loginService.register(registerRequest).subscribe(data=>{
      window.alert("logind success");
      this.moveToLogin();
    })

  }

  moveToRegister(){
    let x = document.getElementById("login");
    let y = document.getElementById("register");
    let z = document.getElementById("btn");

    x.style.left = "-400px";
    y.style.left = "50px";
    z.style.left = "110px";
  }

  moveToLogin(){
    let x = document.getElementById("login");
    let y = document.getElementById("register");
    let z = document.getElementById("btn");

    x.style.left = "50px";
    y.style.left = "450px";
    z.style.left = "0";
  }

}
