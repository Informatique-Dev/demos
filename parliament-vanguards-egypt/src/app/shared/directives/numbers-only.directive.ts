import { Directive, HostListener, Input } from '@angular/core';
import { NgControl } from '@angular/forms';

@Directive({
    selector: '[appInputNumberOnly]',
    standalone: true
})
export class NumbersOnlyDirective {
  @Input() onlyNumber: boolean = false;
  @Input() fraction: boolean = false;
  @Input() noWhiteSpace: boolean = false;
  private ngControl: NgControl;

  constructor(private ngControls: NgControl) {
    this.ngControl = ngControls;
  }

  @HostListener('input', ['$event.target.value'])
  onInput(value: string): void {
    if (this.onlyNumber) {
      this.ngControl?.control?.patchValue(value.replace(/[^0-9]/g, ''));
    }
    if (this.fraction) {
      this.ngControl?.control?.patchValue(
        value
          .replace(/[^0-9.]/g, '')
          .replace(/(\..*)\./g, '$1')
          .replace(/^[0.]*$/g, '')
      );
    }
    if (this.noWhiteSpace) {
      this.ngControl?.control?.patchValue(value.replace(/ +/g, ' '));

      if (value[0] === ' ') {
        this.ngControl?.control?.setValue(value[0].trim());
      }
    }
  }
}
