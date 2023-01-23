import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { AngularMaterialModule } from './angular-material/angular-material.module';



@NgModule({
  declarations: [],
  imports: [
    CommonModule,
    AngularMaterialModule,
],
exports: [
  CommonModule,
  AngularMaterialModule
],
})
export class SharedModule { }
