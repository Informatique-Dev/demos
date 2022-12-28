import { Component, Input } from '@angular/core';
import { MatSidenav } from '@angular/material/sidenav';
import { Router } from '@angular/router';
import { TranslateService } from '@ngx-translate/core';
@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.scss'],
})
export class HeaderComponent {
  @Input() sideNavItem!: MatSidenav;
  lang: string = 'en';

  constructor(private router: Router, private translate: TranslateService) {
    this.lang = this.translate.currentLang;
  }
  clickMenu() {
    this.sideNavItem.toggle();
  }
  openHome() {
    this.router.navigate(['/home']);
  }
  selectTranslate() {
    if (this.lang == 'en') {
      localStorage.setItem('lang', 'ar');
    } else {
      localStorage.setItem('lang', 'en');
    }
    window.location.reload();
  }
}
