import { Component, Input} from '@angular/core';
import { MatSidenav } from '@angular/material/sidenav';
import { Router } from '@angular/router';
import { SideBarComponent } from '../side-bar/side-bar.component';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.scss']
})
export class HeaderComponent {
  @Input() sideNavItem!: MatSidenav;
  sidebar!: SideBarComponent;
  constructor(private router: Router) { }

   openSidebar() {
    this.sideNavItem.toggle();
  }

  openHome() {
    this.router.navigate(['/home']);
  }

}
