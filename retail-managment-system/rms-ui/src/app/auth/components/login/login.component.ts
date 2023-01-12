import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { User } from 'src/app/domain/login/user';
import { MatSnackBar } from '@angular/material/snack-bar';
import { TranslateService } from '@ngx-translate/core';
import { AuthService } from '../../services/auth.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {
  loginForm!: FormGroup;
  hide: boolean = true;
  loginUsers:User[]=[];
   
   constructor(private formBuilder : FormBuilder,
    private authservice :AuthService,) { 
   
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
   this.authservice.storeUsers();
  }

  login(user: User)
  {
   this.authservice.logIn(user)
  }
}


