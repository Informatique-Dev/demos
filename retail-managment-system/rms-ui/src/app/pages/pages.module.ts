import { InvestorPageComponent } from './investor-page/investor-page.component';
import { NgModule } from '@angular/core';
import { HomePageComponent } from './home-page/home-page.component';
import { RouterModule, Routes } from '@angular/router';
import { SharedModule } from '../shared/shared.module';
import { CategoryPageComponent } from './category-page/category-page.component';
import { ProductComponent } from './product/product.component';
import { CustomersComponent } from './customers/customers.component';
import { ProfitComponent } from './profits/profits/profit.component';
import { TranslateModule } from '@ngx-translate/core';
import { AuthGuard } from '../auth/guards/auth.guard';
import { InstallmentComponent } from './installments/installment/installment.component';
import { NgxPrintModule } from 'ngx-print';


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
  { path: 'profits', component: ProfitComponent },
  { path: 'investor', component: InvestorPageComponent },
  { path: 'installment', component: InstallmentComponent },
];
@NgModule({
  declarations: [
    HomePageComponent,
    ProductComponent,
    CategoryPageComponent,
    CustomersComponent,
    ProfitComponent,
    InvestorPageComponent,
    InstallmentComponent,
  ],

  imports: [RouterModule.forChild(routes), SharedModule, TranslateModule,NgxPrintModule],
 
})
export class PagesModule {}
