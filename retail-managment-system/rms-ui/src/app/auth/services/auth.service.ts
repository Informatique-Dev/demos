import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { User } from 'src/app/domain/login/user';
import { MatSnackBar } from '@angular/material/snack-bar';
import { TranslateService } from '@ngx-translate/core';
@Injectable({
  providedIn: 'root',
})
export class AuthService {
  users:User[]=[];
  loginUsers:User[]=[];

  constructor( private router:Router ,
    private _snackBar: MatSnackBar,
    private translate : TranslateService)
  {
    this.users=[
      {email:"asmaa@gmail.com", password:"123"},
      {email:"dina@gmail.com", password:"555"},
      {email:"bosy@gmail.com", password:"444"},
      {email:"raghda@gmail.com", password:"666"}
    ]
  }
  setToken(): void {
    return localStorage.setItem('token', 'any value');
  }

  storeUsers()
  {
    localStorage.setItem('loginUsers', JSON.stringify(this.users));
  }

  logIn(user : User)
  {
    if (localStorage.getItem('loginUsers')) {
      this.loginUsers=JSON.parse(localStorage.getItem("loginUsers")!);

      if(this.loginUsers.find(u=>u.email==user.email))
         {
           if(this.loginUsers.find(u=>u.password==user.password))
           {
            localStorage.setItem("isLogged","Userlogged");
            this.router.navigateByUrl('/home');
           
           }
           else
           {
            this._snackBar.open(this.translate.instant('logIn.password-error'), this.translate.instant('logIn.close'), {
              duration: 2000,
            });
             return;
           }
         }

         else
         {
          this._snackBar.open(this.translate.instant('logIn.email-error'), this.translate.instant('logIn.close'), {
            duration: 2000,
          });
           return;
         }
    }
  }

  getToken() {
    return localStorage.getItem("isLogged")
  }

  logOut()
  {
    localStorage.removeItem("isLogged")
    this.router.navigateByUrl('/login')
  }

}
