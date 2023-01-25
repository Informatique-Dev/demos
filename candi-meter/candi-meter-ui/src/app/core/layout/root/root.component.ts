import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-root',
  templateUrl: './root.component.html',
  styleUrls: ['./root.component.scss'],
  styles: [
    '.side-nav { width: 200px; box-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.2), 0 6px 20px 0 rgba(0, 0, 0, 0.5);}',
  ],
})
export class RootComponent implements OnInit {

  constructor() { }

  ngOnInit(): void {
  }

}
