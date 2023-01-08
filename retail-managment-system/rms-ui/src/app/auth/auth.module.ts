import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { LoginComponent } from './components/login/login.component';
import { SharedModule } from '../shared/shared.module';
import { RouterModule, Routes } from '@angular/router';

const routes: Routes = [
  { path: 'login', component: LoginComponent},
]
@NgModule({
  declarations: [LoginComponent],
  imports: [RouterModule.forChild(routes) ,CommonModule , SharedModule , RouterModule],
})
export class AuthModule {}
