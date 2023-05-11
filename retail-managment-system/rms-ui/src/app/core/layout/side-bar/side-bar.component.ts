import { Component } from '@angular/core';

@Component({
  selector: 'app-side-bar',
  templateUrl: './side-bar.component.html',
  styles: [
    '.active-link { background-color: #002d40; color: white; padding:10px; width:fit-content} .active { background-color: #002d40; color: white; padding: 10px;} .mat-divider { border-top-width: 3px; border-color: #002d40;} .mat-expansion-panel-header{padding:0 57px;} .mat-expansion-panel:not(.mat-expanded) .mat-expansion-panel-header:hover:not([aria-disabled=true]) {background:none;}',
  ],
})
export class SideBarComponent {
  constructor() {}
}
