import { Component, OnInit } from '@angular/core';
import { TranslateService } from '@ngx-translate/core';
import { Component, OnInit } from '@angular/core';
import { TranslateService } from '@ngx-translate/core';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss'],
})
export class AppComponent implements OnInit {
  title = 'rms-ui';
  lang: string;
  constructor(private translate: TranslateService) {
    this.lang = localStorage.getItem('lang') || 'en';
    this.translate.use(this.lang);
  }
}
