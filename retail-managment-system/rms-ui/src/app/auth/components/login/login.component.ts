import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { User } from 'src/app/domain/login/user';
import { MatSnackBar } from '@angular/material/snack-bar';

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
   
   constructor(private formBuilder : FormBuilder,
    private router:Router ,
    private _snackBar: MatSnackBar,) { 
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
            this._snackBar.open('Password is Not correct!', 'Close', {
              duration: 2000,
            });
             return;
           }
         }

         else
         {
          this._snackBar.open('Email is Not Found!', 'Close', {
            duration: 2000,
          });
           return;
         }
    }
  }

  }


