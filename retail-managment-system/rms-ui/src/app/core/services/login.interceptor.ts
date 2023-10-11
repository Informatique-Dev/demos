import { Inject, Injectable,Injector } from '@angular/core';
import {
  HttpRequest,
  HttpHandler,
  HttpEvent,
  HttpInterceptor
} from '@angular/common/http';
import { Observable } from 'rxjs';
import { AuthService } from 'src/app/auth/services/auth.service';

@Injectable()
export class LoginInterceptor implements HttpInterceptor {

  constructor(@Inject(Injector) private readonly injector: Injector) {}
  intercept(request: HttpRequest<unknown>, next: HttpHandler): Observable<HttpEvent<unknown>> {
    const auth=this.injector.get(AuthService);
    request.clone({
        setHeaders: {
        Authorization: `Bearer ${auth.getToken()}`
      } 
    })
    return next.handle(request);
  }
}
