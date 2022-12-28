import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { RouterModule } from '@angular/router';
import { MaterialModuleModule } from './material-module/material-module.module';
import { ConfirmDialogComponent } from './components/confirm-dialog/confirm-dialog.component';
import { TranslateModule } from '@ngx-translate/core';
import { NumbersOnlyDirective } from './directives/numbers-only.directive';

@NgModule({
  declarations: [ConfirmDialogComponent, NumbersOnlyDirective],
  imports: [
    MaterialModuleModule,
    CommonModule,
    HttpClientModule,
    ReactiveFormsModule,
    FormsModule,
    RouterModule,
    TranslateModule,
  ],
  exports: [
    MaterialModuleModule,
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule,
    CommonModule,
  ],
})
export class SharedModule {}
