import { Directive, HostListener, Input } from '@angular/core';

@Directive({
    selector: '[appInputArabicCharOnly]',
    standalone: true
})
export class ArabicCharOnlyDirective {
  @Input() arabicCharOnly: boolean = false;
  @Input() arabicCharOnlyWithoutNumber: boolean = false;

  constructor() {}

  @HostListener('keypress', ['$event']) onkeypress(event: KeyboardEvent): void {
    let e = <KeyboardEvent>event;
    var regex = new RegExp('');
    if (this.arabicCharOnlyWithoutNumber) {
      regex = new RegExp('[ء-ي ]+$');
    }
    if (this.arabicCharOnly) {
      regex = new RegExp('[ء-ي0-9 ]+$');
    }
    var str = String.fromCharCode(!e.charCode ? e.which : e.charCode);
    if (regex.test(str)) {
      return;
    }

    e.preventDefault();
    return;
  }
}
