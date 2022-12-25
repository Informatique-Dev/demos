import { InvestorPageComponent } from './investor-page/investor-page.component';
import { NgModule } from '@angular/core';
import { HomePageComponent } from './home-page/home-page.component';
import { RouterModule, Routes } from '@angular/router';
import { SharedModule } from '../shared/shared.module';
import { CategoryPageComponent } from './category-page/category-page.component';
import { ProductComponent } from './product/product.component';
import { CustomersComponent } from './customers/customers.component';
<<<<<<< HEAD
import { AuthGuard } from '../auth/guards/auth.guard';
import { InstallmentComponent } from './installments/installment/installment.component';

=======
import { InstallmentComponent } from './installments/installment/installment.component';
>>>>>>> 72c836371e9bafe4056dfcb7ec4d592e6f02f8b4

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
  { path: 'installment', component: InstallmentComponent },
];
@NgModule({
  declarations: [
    HomePageComponent,
    ProductComponent,
    CategoryPageComponent,
    CustomersComponent,
    InvestorPageComponent,
    InstallmentComponent,
  
  ],

  imports: [RouterModule.forChild(routes), SharedModule],
})
export class PagesModule {}
