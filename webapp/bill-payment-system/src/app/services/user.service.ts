import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { User } from '../model/user.model';

@Injectable({
  providedIn: 'root'
})
export class UserService {
  credentials: string;
  accessToken: string = '';
  constructor(private httpClient: HttpClient) { }

  signupNewUser(user:User):Observable<any> {
    return this.httpClient.post<any>("http://localhost:8090/users",user);
  }

  doesUserExist(username:string){
    return this.httpClient.get<boolean>(`http://localhost:8090/users/${username}`);
  }

}
