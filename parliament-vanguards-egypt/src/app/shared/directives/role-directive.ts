import { Directive, ElementRef, Input, OnDestroy, OnInit, TemplateRef, ViewContainerRef } from '@angular/core';
import { Subscription } from 'rxjs/internal/Subscription';
import { distinctUntilChanged } from 'rxjs/operators';
import { AuthService } from 'src/app/auth/services/auth.service';

@Directive({
    selector: '[appRole]',
    standalone: true
})
export class RoleDirective implements OnInit, OnDestroy {
  @Input() appRole: 'USER' | 'ADMIN' | 'MANAGER';
  private subscription = new Subscription();
  elementRef: ElementRef;
  constructor(private template: TemplateRef<any>, private view: ViewContainerRef, private authService: AuthService) {}

  ngOnInit() {
    this.subscription.add(
      this.authService.isLoggedIn$.pipe(distinctUntilChanged()).subscribe(res => {
        var roles = localStorage.getItem('roles');
        //@ts-ignore
        for (var item of JSON.parse(roles)) {
          for (var pageRole of this.appRole) {
            if (item.authority === pageRole) {
              this.view.createEmbeddedView(this.template);
              this.elementRef.nativeElement.nextElementSibling.classList.add('new-class');
            } else {
              this.view.clear();
            }
          }
        }
      })
    );
  }

  ngOnDestroy() {
    this.subscription.unsubscribe();
  }
}
