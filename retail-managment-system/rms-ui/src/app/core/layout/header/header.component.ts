import { Component, Input } from '@angular/core';
import { MatSidenav } from '@angular/material/sidenav';
import { Router } from '@angular/router';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.scss'],
})
export class HeaderComponent {
  @Input() sideNavItem!: MatSidenav;
  constructor(private router: Router) {}
  clickMenu() {
    this.sideNavItem.toggle();
  }
  openHome() {
    this.router.navigate(['/home']);
  }
}
