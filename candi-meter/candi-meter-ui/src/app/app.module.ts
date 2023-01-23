import { HttpClientModule } from '@angular/common/http';
import { APP_INITIALIZER, NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { CoreModule } from './core/core.module';
import { ConfigService } from './core/services/config.service';
import { SharedModule } from './shared/shared.module';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';







export function configServiceFactory(
  config:ConfigService
): () => Promise<boolean> {
  return (): Promise<boolean> => config.load();
}
@NgModule({
  declarations: [
    AppComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    SharedModule, 
    BrowserAnimationsModule,
     CoreModule,
  
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
