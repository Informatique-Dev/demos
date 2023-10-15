import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { User } from 'src/app/domain/login/models/user';
import { AuthService } from '../../services/auth.service';
import { LoginRepository } from '../../../domain/login/login.repository';
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
    private authService :AuthService,private router:Router,private loginRepository:LoginRepository) { 
   
  }

  ngOnInit(): void {
    this.loginmentForm();
  }

  loginmentForm() {
    this.loginForm = this.formBuilder.group({
      username: ['' , [Validators.required]],
      password: ['' , [Validators.required]],
    });
  }

  login(user: User)
  {
   this.loginRepository.add(user).subscribe((response)=>{
    if(response.message!=""){
      this.authService.setToken(response.accessToken);
      this.router.navigate(['/home']);
    }
    else{
      this.router.navigate(['/login']);
    }
  })
  }
}


