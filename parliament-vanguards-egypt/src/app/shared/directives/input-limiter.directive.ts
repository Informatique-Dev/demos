import { Directive, ElementRef, HostListener, Input } from '@angular/core';

@Directive({
  selector: '[appInputLimiter]'
})
export class InputLimiterDirective {
  // @ts-ignore
  @Input() maxLength: number;
  constructor(private el: ElementRef) {}
  @HostListener('keydown', ['$event']) onKeyDown(e: { which: number; preventDefault: () => void }) {
    const element = this.el.nativeElement;
    if (element.value.length >= this.maxLength) {
      if (e.which !== 8 && e.which !== 9 && e.which !== 46) {
        e.preventDefault();
      }
    }
  }
}
