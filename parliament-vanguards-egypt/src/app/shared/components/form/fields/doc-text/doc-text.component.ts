import { Component, Input, OnInit } from '@angular/core';
import { FormControl, FormsModule, ReactiveFormsModule } from '@angular/forms';
import { ControlMessagesComponent } from '../../validation/control-messages/control-messages.component';
import { NgIf } from '@angular/common';
import { MatInputModule } from '@angular/material/input';
import { MatFormFieldModule } from '@angular/material/form-field';

@Component({
    selector: 'app-doc-text',
    templateUrl: './doc-text.component.html',
    standalone: true,
    imports: [MatFormFieldModule, MatInputModule, FormsModule, ReactiveFormsModule, NgIf, ControlMessagesComponent]
})
export class DocTextComponent implements OnInit {
  // @ts-ignore
  @Input() control: FormControl;
  @Input() fieldName: string = '';
  @Input() type: string = '';
  @Input() hint: string = '';
  @Input() fun: Function = () => {};
  // @ts-ignore
  @Input() maxLength: number;

  constructor() {}

  ngOnInit(): void {}
}
