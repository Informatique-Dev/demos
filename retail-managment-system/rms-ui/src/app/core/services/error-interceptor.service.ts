import { Injectable } from '@angular/core';
import {
  HttpErrorResponse,
  HttpEvent,
  HttpHandler,
  HttpInterceptor,
  HttpRequest,
  HttpResponse,
} from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { catchError, map, tap } from 'rxjs/operators';
import {
  MatSnackBar,
  MatSnackBarHorizontalPosition,
  MatSnackBarVerticalPosition,
} from '@angular/material/snack-bar';

@Injectable()
export class ErrorInterceptorService implements HttpInterceptor {
  horizontalPosition: MatSnackBarHorizontalPosition = 'start';
  verticalPosition: MatSnackBarVerticalPosition = 'bottom';

  constructor(private _snackBar: MatSnackBar) {}

  intercept(
    request: HttpRequest<any>,
    next: HttpHandler
  ): Observable<HttpEvent<any>> {
    console.log('Passed through the interceptor in request');

    return next.handle(request).pipe(
      catchError((error: HttpErrorResponse) => {
        let errorMsg = '';
        if (error.error instanceof ErrorEvent) {
          alert('Please try again');
          errorMsg = `Error: ${error.error.message}`;
        } else {
          if (error.status === 409) {
            this._snackBar.open('Error 409: Please try again!', 'Close', {
              horizontalPosition: this.horizontalPosition,
              verticalPosition: this.verticalPosition,
            });
          }
          if (error.status === 404) {
            this._snackBar.open(
              'Error 406: Please enter Right data!',
              'Close',
              {
                horizontalPosition: this.horizontalPosition,
                verticalPosition: this.verticalPosition,
              }
            );
          }
          if (error.status === 406) {
            this._snackBar.open(
              'Error 406: Please enter Right data!',
              'Close',
              {
                horizontalPosition: this.horizontalPosition,
                verticalPosition: this.verticalPosition,
              }
            );
          }
          if (error.status === 401) {
            this._snackBar.open(
              'Error 401: You do not have permission, please try again!',
              'Close',
              {
                horizontalPosition: this.horizontalPosition,
                verticalPosition: this.verticalPosition,
              }
            );
          }
          if (error.status === 422) {
            this._snackBar.open(
              'Error 422: Cannot close before specified time!',
              'Close',
              {
                horizontalPosition: this.horizontalPosition,
                verticalPosition: this.verticalPosition,
              }
            );
          }
          if (error.status === 424) {
            this._snackBar.open(
              'Error 424: Please make sure the data is correct!',
              'Close',
              {
                horizontalPosition: this.horizontalPosition,
                verticalPosition: this.verticalPosition,
              }
            );
          }
          if (error.status === 400) {
            this._snackBar.open(
              'Error 400: Your request could not be implement, there is data associated with it!',
              'Close',
              {
                horizontalPosition: this.horizontalPosition,
                verticalPosition: this.verticalPosition,
              }
            );
          }

          if (error.status === 500) {
            this._snackBar.open(
              'Error 500: An error occurred communicating with the server, please try again!',
              'Close',
              {
                horizontalPosition: this.horizontalPosition,
                verticalPosition: this.verticalPosition,
              }
            );
          }
          errorMsg = `Error Code: ${error.status},  Message: ${error.message}`;
        }
        console.log(errorMsg);
        return throwError(errorMsg);
      })
    );
  }
}
