import { NgModule, Optional, SkipSelf,Injector } from '@angular/core';
import { SharedModule } from 'src/app/shared/shared.module';
import { RouterModule } from '@angular/router';
import { CommonModule } from '@angular/common';
import { HeaderComponent } from './layout/header/header.component';
import { FooterComponent } from './layout/footer/footer.component';
import { RootComponent } from './layout/root/root.component';
import { HTTP_INTERCEPTORS } from '@angular/common/http';
import { ErrorInterceptorService } from './services/error-interceptor.service';
import { SideBarComponent } from './layout/side-bar/side-bar.component';
import { TranslateModule } from '@ngx-translate/core';
import { LoginInterceptor } from './services/login.interceptor';

@NgModule({
  declarations: [
    HeaderComponent,
    FooterComponent,
    RootComponent,
    SideBarComponent,
  ],
  imports: [RouterModule, SharedModule, CommonModule, TranslateModule],
  providers: [
    {
      provide: HTTP_INTERCEPTORS,
      useClass: ErrorInterceptorService,
      multi: true,
    },
     {
      provide:HTTP_INTERCEPTORS,
      useClass:LoginInterceptor,
      multi:true,
    } 
  ],
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
