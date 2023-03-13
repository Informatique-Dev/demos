import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { RouterModule } from '@angular/router';
import { MaterialModuleModule } from './material-module/material-module.module';
import { ConfirmDialogComponent } from './components/confirm-dialog/confirm-dialog.component';
import { TranslateModule } from '@ngx-translate/core';
import { NumbersOnlyDirective } from './directives/numbers-only.directive';
import { LoadingInterceptor } from '../core/services/loading.interceptor';
import { LoadingSpinnerComponent } from './components/loading-spinner/loading-spinner.component';


@NgModule({
  declarations: [ConfirmDialogComponent, NumbersOnlyDirective, LoadingSpinnerComponent],
  imports: [
  MaterialModuleModule,
    CommonModule,
    HttpClientModule,
    ReactiveFormsModule,
    FormsModule,
    RouterModule,
    TranslateModule,
  ],
  providers: [
    {
        provide: HTTP_INTERCEPTORS,
        useClass: LoadingInterceptor,
        multi: true
    }
],
  exports: [
    MaterialModuleModule,
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule,
    CommonModule,
    LoadingSpinnerComponent
  ],
})
export class SharedModule {}
