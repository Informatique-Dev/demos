import { PopUpInvestorComponent } from './investor-page/pop-up/pop-up.component';
import { InvestorPageComponent } from './investor-page/investor-page.component';
import { NgModule } from '@angular/core';
import { HomePageComponent } from './home-page/home-page.component';
import { RouterModule, Routes } from '@angular/router';
import { SharedModule } from '../shared/shared.module';
import { CategoryPageComponent } from './category-page/category-page.component';
import { PopUpComponent } from './category-page/pop-up/pop-up.component';
import { ProductComponent } from './product/product.component';
import { CustomersComponent } from './customers/customers.component';

const routes: Routes = [
  { path: '', redirectTo: 'home' },
  { path: 'home', component: HomePageComponent },
  { path: 'product', component: ProductComponent },
  { path: 'category', component: CategoryPageComponent },
  { path: 'customers', component: CustomersComponent },
  { path: 'investor', component: InvestorPageComponent },
];
@NgModule({
  declarations: [
    ProductComponent,
    CategoryPageComponent,
    PopUpComponent,
    CustomersComponent,
    InvestorPageComponent,
  ],

  imports: [RouterModule.forChild(routes), SharedModule],
})
export class PagesModule {}
