import { Component, Input } from '@angular/core';
import { FormControl, FormsModule, ReactiveFormsModule } from '@angular/forms';
import { ControlMessagesComponent } from '../../validation/control-messages/control-messages.component';
import { TextFieldModule } from '@angular/cdk/text-field';
import { MatInputModule } from '@angular/material/input';
import { MatFormFieldModule } from '@angular/material/form-field';
import { NgIf } from '@angular/common';

@Component({
    selector: 'app-text-area',
    templateUrl: './text-area.component.html',
    styles: [
        `
      .makeArrows::-webkit-inner-spin-button {
        -webkit-appearance: none;
      }
      .makeArrows[type='number'] {
        -moz-appearance: textfield;
      }
      .form-group {
    display: flex;
    flex-direction: column;
  }
  ::ng-deep .mdc-notched-outline__leading {
    border: 1px solid rgba(122, 73, 13, 0.541) !important;
    border-left: none !important;
    border-width: 3px !important;
    border-radius: 0px 15px 15px 0px !important;
    color: rgba(122, 73, 13, 0.541) !important;
  
  }
  
  ::ng-deep .mdc-notched-outline__notch {
    border: 1px solid rgba(122, 73, 13, 0.541) !important;
    border-width: 3px !important;
    color: rgba(122, 73, 13, 0.541) !important;
    border-left : none !important;
  }

  ::ng-deep div.mdc-notched-outline__notch{
    border-left : none !important;
    border-right : none !important;

  }
  
  ::ng-deep .mdc-notched-outline__trailing {
    border: 1px solid rgba(122, 73, 13, 0.541) !important;
    border-right: none !important;
    border-width: 3px !important;
    border-radius: 15px 0 0 15px !important;
    color: rgba(122, 73, 13, 0.541) !important;
  
  }

  input{
    font-weight: bold !important;
    color: #8b4513 !important;
    text-align: center;
    font-family: 'Cairo', sans-serif !important; 
  }
  mat-label {
    font-weight: bold;
    color: #8b4513;
    font-family: 'Cairo', sans-serif !important;
  }

    `
    ],
    standalone: true,
    imports: [NgIf, MatFormFieldModule, MatInputModule, TextFieldModule, FormsModule, ReactiveFormsModule, ControlMessagesComponent]
})
export class TextAreaComponent {
@Input() control: FormControl = new FormControl();
@Input() fieldName: string = '';
@Input() hint: string = '';
// @ts-ignore
@Input() maxLength: number;
@Input() cdkAutosizeMaxRows: number=10;

// @ts-ignore
@Input() cdkAutosizeMinRows: number=5;
@Input() label: boolean = false;

  constructor() { }
}
