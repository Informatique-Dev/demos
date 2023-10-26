import { Component, Input, OnInit } from '@angular/core';
import { FormControl } from '@angular/forms';
import { ValidationMessagesService } from 'src/app/core/services/validation-messages.service';
import { MatFormFieldModule } from '@angular/material/form-field';
import { NgIf } from '@angular/common';

@Component({
    selector: 'app-control-messages',
    templateUrl: './control-messages.component.html',
    styleUrls: ['./control-messages.component.scss'],
    standalone: true,
    imports: [NgIf, MatFormFieldModule]
})
export class ControlMessagesComponent implements OnInit {
  @Input() control: FormControl = new FormControl();
  @Input() fieldName: string | undefined;
  @Input() showMessage: boolean = false;
  @Input() fieldNameWillNotShowMessage: string = '';
  @Input() requiredLength: number;
  @Input() minLength: number;
  constructor() {}
  ngOnInit(): void {}
  get errorMessage() {
    for (let propertyName in this.control?.errors) {
      if (this.control.errors.hasOwnProperty(propertyName) && this.control.touched) {
        return ValidationMessagesService.getValidatorErrorMessage(
          propertyName,
          this.control.errors[propertyName],
          this.fieldName
        );
      }
    }

    return null;
  }
}
