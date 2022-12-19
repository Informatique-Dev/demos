import { Directive, ElementRef, HostListener } from '@angular/core';

@Directive({
  selector: '[NumbersOnly]',
})
export class NumbersOnlyDirective {
  constructor(private elementRef: ElementRef) {}

  @HostListener('input', ['$event']) onInputChange() {
    const initalValue = this.elementRef.nativeElement.value;
    this.elementRef.nativeElement.value = initalValue.replace(/[^0-9]*/g, '');
  }
}
