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
    return next.handle(request).pipe(
      catchError((error: HttpErrorResponse) => {
        let errorMsg = '';
        if (error.error instanceof ErrorEvent) {
          this.notFoundMessage();
        } else {
          if (error.status === 409) this.conflictMessage();
          if (error.status === 404) this.notFoundMessage();
          if (error.status === 406) this.errormessage();
          if (error.status === 401) this.unauthorized();
          if (error.status === 422) this.Unprocessable();
          if (error.status === 424) this.wrongDataMessage();
          if (error.status === 400) this.hasDependentsMessage();
          if (error.status === 500) this.serverErrorMessage();
          errorMsg = `Error Code: ${error.status},  Message: ${error.message}`;
        }
        return throwError(errorMsg);
      })
    );
  }

  private conflictMessage(): void {
    this._snackBar.open('Error 409: Please do not duplicate data!', 'Close');
  }

  private notFoundMessage(): void {
    this._snackBar.open(
      'The requested URL/error was not found on this server!',
      'Close'
    );
  }
  private errormessage(): void {
    this._snackBar.open('Error 406: Please enter Right data!', 'Close');
  }
  private unauthorized(): void {
    this._snackBar.open(
      'Error 401: You do not have permission, please try again!',
      'Close'
    );
  }
  private Unprocessable(): void {
    this._snackBar.open(
      'Error 422: Cannot close before specified time!',
      'Close'
    );
  }
  private wrongDataMessage(): void {
    this._snackBar.open(
      'Error 424: Please make sure the data is correct!',
      'Close'
    );
  }

  private hasDependentsMessage(): void {
    this._snackBar.open(
      'Error 400: This is an input error, Please enter Right data!',
      'Close'
    );
  }
  private serverErrorMessage(): void {
    this._snackBar.open(
      'Error 500: An error occurred communicating with the server, please try again!',
      'Close'
    );
  }

  private conflictMessage(): void {
    this._snackBar.open('Error 409: Please do not duplicate data!', 'Close');
  }

  private notFoundMessage(): void {
    this._snackBar.open(
      'The requested URL/error was not found on this server!',
      'Close'
    );
  }
  private errormessage(): void {
    this._snackBar.open('Error 406: Please enter Right data!', 'Close');
  }
  private unauthorized(): void {
    this._snackBar.open(
      'Error 401: You do not have permission, please try again!',
      'Close'
    );
  }
  private Unprocessable(): void {
    this._snackBar.open(
      'Error 422: Cannot close before specified time!',
      'Close'
    );
  }
  private wrongDataMessage(): void {
    this._snackBar.open(
      'Error 424: Please make sure the data is correct!',
      'Close'
    );
  }

  private hasDependentsMessage(): void {
    this._snackBar.open(
      'Error 400: This is an input error, Please enter Right data!',
      'Close'
    );
  }
  private serverErrorMessage(): void {
    this._snackBar.open(
      'Error 500: An error occurred communicating with the server, please try again!',
      'Close'
    );
  }

  private conflictMessage(): void {
    this._snackBar.open('Error 409: Please do not duplicate data!', 'Close');
  }

  private notFoundMessage(): void {
    this._snackBar.open(
      'Error 404: The requested URL/error was not found on this server!'
    );
  }
  private errormessage(): void {
    this._snackBar.open('Error 406: Please enter Right data!');
  }
  private unauthorized(): void {
    this._snackBar.open(
      'Error 401: You do not have permission, please try again!'
    );
  }
  private Unprocessable(): void {
    this._snackBar.open('Error 422: Cannot close before specified time!');
  }
  private wrongDataMessage(): void {
    this._snackBar.open('Error 424: Please make sure the data is correct!');
  }

  private hasDependentsMessage(): void {
    this._snackBar.open(
      'Error 400: This is an input error, Please enter Right data!'
    );
  }
  private serverErrorMessage(): void {
    this._snackBar.open(
      'Error 500: An error occurred communicating with the server, please try again!'
    );
  }
}
