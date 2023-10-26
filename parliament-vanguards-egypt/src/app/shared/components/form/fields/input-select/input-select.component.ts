import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { FormControl, FormsModule, ReactiveFormsModule } from '@angular/forms';
import { ControlMessagesComponent } from '../../validation/control-messages/control-messages.component';
import { RouterLink } from '@angular/router';
import { MatOptionModule } from '@angular/material/core';
import { MatSelectModule } from '@angular/material/select';
import { NgIf, NgFor } from '@angular/common';
import { MatFormFieldModule } from '@angular/material/form-field';

@Component({
    selector: 'app-input-select',
    templateUrl: './input-select.component.html',
    styleUrls: ['./input-select.component.css'],
    standalone: true,
    imports: [MatFormFieldModule, NgIf, MatSelectModule, FormsModule, ReactiveFormsModule, MatOptionModule, NgFor, RouterLink, ControlMessagesComponent]
})
export class InputSelectComponent implements OnInit {
  // @ts-ignore
  @Input() control: FormControl;
  // @ts-ignore
  @Input() fieldName: string;
  @Input() data: any;
  @Input() routerLink: string;
  // @ts-ignore
  @Input() selectedValue: string;
  // @ts-ignore
  @Input() viewedValue: string;
   // @ts-ignore
  @Input() RequestLabel: boolean=false;
  @Input() degreeLabel: boolean=false;
  @Input() prosecutionLabel: boolean=false;
  @Input() offerLabel : boolean =false
  @Input() chooseLabel : boolean =false
  @Input() tripLabel: boolean=false;
  // @ts-ignore
  compareFu = (a, b): boolean => a?.id === b?.id;
  constructor() {}

  ngOnInit(): void {}

}
