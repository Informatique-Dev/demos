import { NgModule, Optional, SkipSelf } from '@angular/core';
import { SharedModule } from 'src/app/shared/shared.module';
import { RouterModule } from '@angular/router';
import { CommonModule } from '@angular/common';
import { HeaderComponent } from './layout/header/header.component';
import { FooterComponent } from './layout/footer/footer.component';
import { RootComponent } from './layout/root/root.component';
import { AddProductComponent } from '../pages/add-product/add-product.component';
import { UpdateProductComponent } from '../pages/update-product/update-product.component';

@NgModule({
  declarations: [
    HeaderComponent,
    FooterComponent,
    RootComponent,
    AddProductComponent,
    UpdateProductComponent,
  ],
  imports: [RouterModule, SharedModule, CommonModule],
})
export class CoreModule {
  constructor(@Optional() @SkipSelf() parentModule: CoreModule) {
    if (parentModule) {
      throw new Error(
        'CoreModule is already loaded. Import it in the AppModule only'
      );
    }
  }
}
