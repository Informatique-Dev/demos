import { NgModule } from '@angular/core';
import { HomePageComponent } from './home-page/home-page.component';
import { RouterModule, Routes } from '@angular/router';
import { SharedModule } from '../shared/shared.module';
import { CategoryPageComponent } from './category-page/category-page.component';
import { PopUpComponent } from './category-page/pop-up/pop-up.component';
import { ProductComponent } from './product/product.component';
import { ManageProductComponent } from './product/manage-product/manage-product.component';
import { CustomersComponent } from './customers/customers.component';
import { ManageCustomersComponent } from './customers/manage-customers/manage-customers.component';

const routes: Routes = [
  { path: '', redirectTo: 'home' },
  { path: 'home', component: HomePageComponent },
  { path: 'product', component: ProductComponent },
  { path: 'customers', component: CustomersComponent },
];
@NgModule({
  declarations: [ProductComponent, ManageProductComponent, CustomersComponent, ManageCustomersComponent],
  imports: [RouterModule.forChild(routes), SharedModule],
})
export class PagesModule {}
