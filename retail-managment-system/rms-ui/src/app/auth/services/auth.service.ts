import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { User } from 'src/app/domain/login/models/user';
@Injectable({
  providedIn: 'root',
})
export class AuthService {
  users:User[]=[];
  loginUsers:User[]=[];
  constructor( private router:Router ){}

  setToken(token:string){
    localStorage.setItem("tokenUser",token);
  }
  getToken() {
    return localStorage.getItem("tokenUser")
  }

  logOut()
  {
    localStorage.removeItem("tokenUser")
    this.router.navigateByUrl('/login')
  }

}
