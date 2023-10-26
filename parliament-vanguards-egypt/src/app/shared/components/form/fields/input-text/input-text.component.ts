import { Component, Input } from '@angular/core';
import { FormControl, FormsModule, ReactiveFormsModule } from '@angular/forms';
import { ControlMessagesComponent } from '../../validation/control-messages/control-messages.component';
import { MatIconModule } from '@angular/material/icon';
import { ExtendedModule } from '@angular/flex-layout/extended';
import { ArabicCharOnlyDirective } from '../../../../directives/arabic-char-only.directive';
import { EnglishCharOnlyDirective } from '../../../../directives/english-char-only.directive';
import { NumbersOnlyDirective } from '../../../../directives/numbers-only.directive';
import { MatInputModule } from '@angular/material/input';
import { MatFormFieldModule } from '@angular/material/form-field';
import { NgIf, NgClass } from '@angular/common';

@Component({
    selector: 'app-input-text',
    templateUrl: './input-text.component.html',
    styles: [
        `
      .makeArrows::-webkit-inner-spin-button {
        -webkit-appearance: none;
      }
      .makeArrows[type='number'] {
        -moz-appearance: textfield;
      }
      form-group {
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
        font-family: 'Cairo', sans-serif!important;
      }
      .login{
        color: #8b4513 !important;
        font-weight: bold
      }

    `
    ],
    standalone: true,
    imports: [NgIf, MatFormFieldModule, MatInputModule, FormsModule, NumbersOnlyDirective, EnglishCharOnlyDirective, ArabicCharOnlyDirective, ReactiveFormsModule, NgClass, ExtendedModule, MatIconModule, ControlMessagesComponent]
})
export class InputTextComponent {
@Input() control: FormControl = new FormControl();
@Input() fieldName: string = '';
// @ts-ignore
@Input() type: 'text' | 'number' |'password';
@Input() hint: string = '';
@Input() fun: Function = () => {};
@Input() onlyNumber: boolean = false;
@Input() englishCharOnly: boolean = false;
@Input() arabicCharOnly: boolean = false;
@Input() arabicCharOnlyWithoutNumber: boolean = false;
@Input() fraction: boolean = false;
@Input() readonly: boolean= false;
// @ts-ignore
@Input() maxLength: number;
// @ts-ignore
@Input() requiredLength: number;
@Input() minLength: number;
@Input() fieldNameWillNotShowMessage: string = '';
@Input() showMessage: boolean = false;
@Input() arrowsNumberInputRemoved: boolean = false;
@Input() noWhiteSpace: boolean = false;
@Input() label: boolean = false;
@Input() value : string
@Input() passwordIcon : boolean =false
@Input() inboxIcon : boolean =false


// @ts-ignore
@Input() min: number;
  constructor() { }
}
