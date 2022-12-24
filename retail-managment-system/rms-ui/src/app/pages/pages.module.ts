import { InvestorPageComponent } from './investor-page/investor-page.component';
import { NgModule } from '@angular/core';
import { HomePageComponent } from './home-page/home-page.component';
import { RouterModule, Routes } from '@angular/router';
import { SharedModule } from '../shared/shared.module';
import { CategoryPageComponent } from './category-page/category-page.component';
import { ProductComponent } from './product/product.component';
import { CustomersComponent } from './customers/customers.component';
import { TranslateModule } from '@ngx-translate/core';
import { AuthGuard } from '../auth/guards/auth.guard';

const routes: Routes = [
  { path: '', redirectTo: 'home' },
  { path: 'home', component: HomePageComponent },
  { path: 'product', component: ProductComponent },
  {
    path: 'category',
    component: CategoryPageComponent,
    canActivate: [AuthGuard],
  },
  { path: 'customers', component: CustomersComponent },
  { path: 'investor', component: InvestorPageComponent },
];
@NgModule({
  declarations: [
    HomePageComponent,
    ProductComponent,
    CategoryPageComponent,
    CustomersComponent,
    InvestorPageComponent,
  ],

  imports: [RouterModule.forChild(routes), SharedModule, TranslateModule],
})
export class PagesModule {}
