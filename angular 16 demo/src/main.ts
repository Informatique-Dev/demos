import { platformBrowserDynamic } from '@angular/platform-browser-dynamic';


import { importProvidersFrom } from '@angular/core';
import { AppComponent } from './app/app.component';
import { AppRoutingModule } from './app/app-routing.module';
import { BrowserModule, bootstrapApplication } from '@angular/platform-browser';
import { Routes, provideRouter, withComponentInputBinding } from '@angular/router';

const routes: Routes = [];
bootstrapApplication(AppComponent, {
    providers: [provideRouter(routes, withComponentInputBinding()),importProvidersFrom(BrowserModule, AppRoutingModule)]
})
  .catch(err => console.error(err));
