import { Component } from '@angular/core';
import { TranslateService } from '@ngx-translate/core';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss'],
})
export class AppComponent {
  title = 'rms-ui';
  lang: string;
  constructor(private translate: TranslateService) {
    this.lang = localStorage.getItem('lang') || 'en';
    this.translate.use(this.lang);
  }
}
