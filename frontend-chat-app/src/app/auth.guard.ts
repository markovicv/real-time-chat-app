import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot, UrlTree } from '@angular/router';
import { Observable } from 'rxjs';
import { JwtHelperService } from '@auth0/angular-jwt';

@Injectable({
  providedIn: 'root'
})
export class AuthGuard implements CanActivate {


  
  canActivate(): boolean  {

    let token = localStorage.getItem("jwt");


    if(token == ""){
      this.router.navigateByUrl("/login");
      return false;


    }
    console.log("nije usao");

    const expiredToken:boolean = this.jwtHelper.isTokenExpired(token);

    if(expiredToken){
      this.router.navigateByUrl("/login");
      return false;
    }

    return true


  }


  constructor(private router:Router,private jwtHelper:JwtHelperService){

  }

 
  
}
