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
  constructor(private router: Router, private translate: TranslateService) {}
  clickMenu() {
    this.sideNavItem.toggle();
  }
  openHome() {
    this.router.navigate(['/home']);
  }
  selectTranslate(event: any) {
    this.translate.use(event.target.value);
  }
}
