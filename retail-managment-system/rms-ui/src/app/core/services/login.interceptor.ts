import { Inject, Injectable,Injector } from '@angular/core';
import {
  HttpRequest,
  HttpHandler,
  HttpEvent,
  HttpInterceptor,
  HttpErrorResponse
} from '@angular/common/http';
import { Observable, catchError, tap, throwError } from 'rxjs';
import { AuthService } from 'src/app/auth/services/auth.service';
import { Router } from '@angular/router';

@Injectable()
export class LoginInterceptor implements HttpInterceptor{
  constructor(@Inject(Injector) private readonly injector: Injector,private router:Router) {}
  private handleAuthError(err: HttpErrorResponse): Observable<any> {
    if (err.status === 401) {
        this.router.navigateByUrl(`/login`);
    }
    return throwError(err);
}
  intercept(request: HttpRequest<unknown>, next: HttpHandler): Observable<HttpEvent<unknown>> {
     const auth=this.injector.get(AuthService);
    let updateRequest=request.clone({
      setHeaders: { Authorization: `Bearer ${auth.getToken()}` }
    }) 
    return next.handle(updateRequest).pipe(catchError(x=> this.handleAuthError(x)))
  
  }

}


