import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { User } from 'src/app/domain/login/user';
import { MatSnackBar } from '@angular/material/snack-bar';
import { TranslateService } from '@ngx-translate/core';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {
  loginForm!: FormGroup;
  hide: boolean = true;
  users:User[]=[];
  loginUsers:User[]=[];
  emailError : string ="";
  passwordError : string ="";
  close :string="";
   
   constructor(private formBuilder : FormBuilder,
    private router:Router ,
    private _snackBar: MatSnackBar,
    private translate : TranslateService) { 
    this.users=[
      {email:"asmaa@gmail.com", password:"123"},
      {email:"dina@gmail.com", password:"555"},
      {email:"bosy@gmail.com", password:"444"},
      {email:"raghda@gmail.com", password:"666"}
    ]
   
  }

  ngOnInit(): void {
    this.loginmentForm();
    this.setUsers();
    this.translateError();
  }

  loginmentForm() {
    this.loginForm = this.formBuilder.group({
      
      email: ['' , [Validators.required]],
      password: ['' , [Validators.required]],
    });
  }

  setUsers(){
    localStorage.setItem('loginUsers', JSON.stringify(this.users));
  }

  login(user : User)
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
            this._snackBar.open(this.passwordError, this.close, {
              duration: 2000,
            });
             return;
           }
         }

         else
         {
          this._snackBar.open(this.emailError, this.close, {
            duration: 2000,
          });
           return;
         }
    }
  }

  translateError ()
  {
    this.emailError= this.translate.instant('logIn.email-error');
    this.passwordError= this.translate.instant('logIn.password-error');
    this.close= this.translate.instant('logIn.close');
  }

  }


