import { HttpEvent, HttpHandler, HttpInterceptor, HttpRequest } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable } from "rxjs";



@Injectable({
    providedIn:'root'   
})
export class Interceptor implements HttpInterceptor{



    intercept(req: HttpRequest<any>, next: HttpHandler):Observable<HttpEvent<any>>{


       
        // const jwt = localStorage.getItem("jwt");
        
        // if(req.url.includes("/auth/login")){
        //     return next.handle(req);
        // }

        // req = req.clone({
        //     setHeaders:{
        //         Authorization:'Bearer '+jwt
        //     }
        // });
        return next.handle(req);
        
    }
}