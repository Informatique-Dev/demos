import { Component } from '@angular/core';
import { LoaderService } from 'src/app/core/services/loader.service';

@Component({
  selector: 'app-loading-spinner',
  templateUrl: './loading-spinner.component.html',
  styleUrls: []
})
export class LoadingSpinnerComponent {

  constructor(public loader: LoaderService) { }

}
