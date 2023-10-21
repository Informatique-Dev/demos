import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import {MatButtonModule} from '@angular/material/button';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { BasicInformationComponent } from './basic-information/basic-information.component';
import { MaterialModuleModule } from './shared/material-module/material-module.module';
import{FormsModule, ReactiveFormsModule} from '@angular/forms';
import { CommunicationMethodsComponent } from './communication-methods/communication-methods.component';
import { EducationalQualificationComponent } from './educational-qualification/educational-qualification.component';
import { AttachmentsComponent } from './attachments/attachments.component';
import { DoneComponent } from './done/done.component';
import { HttpClientModule } from '@angular/common/http';
import { StepperComponent } from './stepper/stepper.component';

@NgModule({
  declarations: [
    AppComponent,
    BasicInformationComponent,
    CommunicationMethodsComponent,
    EducationalQualificationComponent,
    AttachmentsComponent,
    DoneComponent,
    StepperComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,MatButtonModule,
    MaterialModuleModule,FormsModule, ReactiveFormsModule,HttpClientModule,
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
