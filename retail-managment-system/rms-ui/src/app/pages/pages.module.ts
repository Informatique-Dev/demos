import { NgModule } from '@angular/core';
import { HomePageComponent } from './home-page/home-page.component';
import { RouterModule, Routes } from '@angular/router';
import { SharedModule } from '../shared/shared.module';
import { ProductComponent } from './product/product.component';
import { ManageProductComponent } from './product/manage-product/manage-product.component';

const routes: Routes = [
  { path: '', redirectTo: 'home' },
  { path: 'home', component: HomePageComponent },
  { path: 'product', component: ProductComponent },
];
@NgModule({
  declarations: [ProductComponent, ManageProductComponent],
  imports: [RouterModule.forChild(routes), SharedModule],
})
export class PagesModule {}
