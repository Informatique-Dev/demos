import { Directive, HostListener, Input } from '@angular/core';
import { NgControl } from '@angular/forms';

@Directive({
    selector: '[appInputEnglishCharOnly]',
    standalone: true
})
export class EnglishCharOnlyDirective {
  @Input() englishCharOnly: boolean = false;

  constructor() {}

  @HostListener('keypress', ['$event']) onkeypress(event: KeyboardEvent): void {
    let e = <KeyboardEvent>event;

    if (this.englishCharOnly) {
      var regex = new RegExp("^[a-zA-Z0-9 ]+$");
      var str = String.fromCharCode(!e.charCode ? e.which : e.charCode);
      if (regex.test(str)) {
          return ;
      }

      e.preventDefault();
      return ;
  }
}
}
