import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { SideBarComponent } from './layout/side-bar/side-bar.component';
import { HeaderComponent } from './layout/header/header.component';
import { RootComponent } from './layout/root/root.component';
import { RouterModule } from '@angular/router';
import { SharedModule } from '../shared/shared.module';
import { FooterComponent } from './layout/footer/footer.component';



@NgModule({
  declarations: [
    SideBarComponent,
    HeaderComponent,
    SideBarComponent,
    RootComponent,
    FooterComponent,

  ],
  imports: [ CommonModule, RouterModule , SharedModule],
})
export class CoreModule { }
