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
import { TransactionComponent } from './transaction/transaction.component';
import { AuthModule } from '../auth/auth.module';
import { NgxPrintModule } from 'ngx-print';
import { EmployeeComponent } from './employee/employee/employee.component';


const routes: Routes = [
  { path: '', redirectTo: 'home' },
  { path: 'home', component: HomePageComponent },
  { path: 'product', component: ProductComponent },
  {path: 'category',component: CategoryPageComponent},
  { path: 'customers', component: CustomersComponent ,canActivate: [AuthGuard] },
  { path: 'profits', component: ProfitComponent ,canActivate: [AuthGuard] },
  { path: 'investor', component: InvestorPageComponent },
  { path: 'installment', component: InstallmentComponent ,canActivate: [AuthGuard] },
  { path: 'transaction', component: TransactionComponent },
  { path: 'employee', component: EmployeeComponent },


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
    TransactionComponent,
    EmployeeComponent,

  ],
  imports: [RouterModule.forChild(routes), SharedModule, TranslateModule,AuthModule,NgxPrintModule],

})
export class PagesModule {}
