import { Component } from '@angular/core';

@Component({
  selector: 'app-side-bar',
  templateUrl: './side-bar.component.html',
  styles: [
    '.active { background-color: #002d40; color: white; padding: 10px;} .mat-divider { border-top-width: 3px; border-color: #002d40; }',
  ],
})
export class SideBarComponent {
  constructor() {}
}
