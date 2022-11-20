import { NgModule } from '@angular/core';
import { HomePageComponent } from './home-page/home-page.component';
import { RouterModule, Routes } from '@angular/router';
import { SharedModule } from '../shared/shared.module';

const routes: Routes = [{ path: '', component: HomePageComponent }];
@NgModule({
  imports: [RouterModule.forChild(routes), SharedModule],
})
export class PagesModule {}
