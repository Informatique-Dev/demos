import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { User } from 'src/app/domain/login/user';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {
  loginForm!: FormGroup;
  hide: boolean = false;
  users:User[]=[];
  loginUsers:User[]=[];

  constructor(private formBuilder : FormBuilder,private router:Router) { 
    this.users=[
      {email:"asmaa@gmail.com", password:"123"},
      {email:"dina@gmail.com", password:"555"},
      {email:"bosy@gmail.com", password:"444"},
      {email:"raghda@gmail.com", password:"666"}
    ]
  }

  ngOnInit(): void {
    this.logForm();
    this.setUsers();
  }

  logForm() {
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
             alert("Password Is Incorrect")
             return;
           }
         }

         else
         {
           alert("Account Not Found")
           return;
         }
    }
  }

  }


