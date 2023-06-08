import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './home/home.component';
import { RegisterComponent } from './register/register.component';
import { AboutComponent } from './about/about.component';
import { DomainsComponent } from './domains/domains.component';

const routes: Routes = [
  {path: "home" ,loadComponent: () => import('./home/home.component').then(m => m.HomeComponent) , resolve: {data: ()=> "test"}},
  {path: "" ,component: HomeComponent , resolve: {data: ()=> "test"}},
  {path: "reg" ,component: RegisterComponent},
  {
    path: 'domains',
    component: DomainsComponent
  },
  {
    path: 'about',
    component: AboutComponent
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
