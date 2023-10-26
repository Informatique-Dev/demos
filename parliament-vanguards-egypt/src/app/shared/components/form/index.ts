import { InputTextComponent } from './fields/input-text/input-text.component';
import { ControlMessagesComponent } from './validation/control-messages/control-messages.component';
import { InputSelectComponent } from './fields/input-select/input-select.component';
import { InputDateComponent } from './fields/input-date/input-date.component';
import { DocTextComponent } from './fields/doc-text/doc-text.component';
import { TextAreaComponent } from './fields/text-area/text-area.component';

export const FormComponents = [
  InputTextComponent,
  ControlMessagesComponent,
  InputSelectComponent,
  InputDateComponent,
  DocTextComponent,
TextAreaComponent
];

export * from './fields/input-text/input-text.component';
export * from './validation/control-messages/control-messages.component';
export * from './fields/input-select/input-select.component';
export * from './fields/input-date/input-date.component';
export * from './fields/doc-text/doc-text.component';
export * from './fields/text-area/text-area.component';
