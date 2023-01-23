import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { SharedModule } from '../shared/shared.module';
import { HomePageComponent } from './home-page/home-page.component';


const routes: Routes = [
  { path: 'home', component: HomePageComponent },
];
@NgModule({
  declarations: [
    HomePageComponent,
  ],
  imports: [RouterModule.forChild(routes), SharedModule],
})
export class PagesModule { }
