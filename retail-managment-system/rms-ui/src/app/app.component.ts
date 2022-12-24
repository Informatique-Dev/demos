import { Component, OnInit } from '@angular/core';
import { TranslateService } from '@ngx-translate/core';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss'],
})
export class AppComponent implements OnInit {
  title = 'rms-ui';
  constructor(private translate: TranslateService) {}
  ngOnInit(): void {
    this.translate.addLangs(['en', 'ar']);
    // this.translate.setDefaultLang('en');

    const browserLang = this.translate.getBrowserLang();
    // @ts-ignore:
    this.translate.use(browserLang.match(/en||ar/) ? browserLang : 'en');
  }
  selectTranslate(event: any) {
    this.translate.use(event.target.value);
  }
}
