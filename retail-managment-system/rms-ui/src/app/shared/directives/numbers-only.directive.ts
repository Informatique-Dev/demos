import { Directive, ElementRef, HostListener } from '@angular/core';

@Directive({
  selector: '[NumbersOnly]',
})
export class NumbersOnlyDirective {
  private regex: RegExp = new RegExp('^[0-9]*$');
  private specialKeys: Array<string> = ['Backspace', 'ArrowLeft', 'ArrowRight'];
  constructor(private elementRef: ElementRef) {}

  /**
   * @param event
   */
  @HostListener('keydown', ['$event']) onKeyDown(event: KeyboardEvent) {
    if (this.specialKeys.indexOf(event.key) !== -1) {
      return;
    }
    const inputValue: string = this.elementRef.nativeElement.value.concat(
      event.key
    );
    if (inputValue && !String(inputValue).match(this.regex)) {
      event.preventDefault();
    }

    return;
  }
}
