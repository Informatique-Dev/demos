import { NgModule } from '@angular/core';
import { HomePageComponent } from './home-page/home-page.component';
import { RouterModule, Routes } from '@angular/router';
import { SharedModule } from '../shared/shared.module';
import { CategoryPageComponent } from './category-page/category-page.component';

const routes: Routes = [
  { path: '', redirectTo: 'home' },
  { path: 'home', component: HomePageComponent },
  { path: 'category', component: CategoryPageComponent },
];
@NgModule({
  imports: [RouterModule.forChild(routes), SharedModule],
})
export class PagesModule {}
