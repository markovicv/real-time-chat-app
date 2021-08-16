import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot, UrlTree } from '@angular/router';
import { Observable } from 'rxjs';
import { JwtHelperService } from '@auth0/angular-jwt';

@Injectable({
  providedIn: 'root'
})
export class AuthGuard implements CanActivate {


  
  canActivate(): boolean  {

    if(this.loggedIn()){
      return true;
    }
    else{
      this.router.navigateByUrl('/login')
      return false;
    }


  }

  loggedIn(){
    return !!localStorage.getItem("jwt");
  }

  constructor(private router:Router){

  }

 
  
}
