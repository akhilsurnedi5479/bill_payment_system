import { Injectable } from '@angular/core';
import { Router, ActivatedRouteSnapshot, RouterStateSnapshot, CanActivate } from '@angular/router';
import { Observable, Observer } from 'rxjs';
import { AuthenticateService } from './services/authentication.service';

@Injectable({
  providedIn: 'root'
})
export class AuthGuardService implements CanActivate {

  constructor(private authService:AuthenticateService,private router:Router) { }

  canActivate(route:ActivatedRouteSnapshot,state:RouterStateSnapshot):Observable<boolean> | Promise<boolean> | boolean{
    this.authService.redirectUrl=state.url;
    return Observable.create((observer:Observer<any>)=>{
      if(this.authService.loggedIn){
        observer.next(true);
      }
      else{
        this.router.navigate(['/login']);
      }
    })
  }
}