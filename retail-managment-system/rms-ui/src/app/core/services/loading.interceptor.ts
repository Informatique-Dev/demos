import { Injectable } from '@angular/core';
import {
  HttpRequest,
  HttpHandler,
  HttpEvent,
  HttpInterceptor
} from '@angular/common/http';
import { Observable } from 'rxjs';
import { finalize } from 'rxjs/operators'
import { LoaderService } from './loader.service';

@Injectable()
export class LoadingInterceptor implements HttpInterceptor {

  private totalRequests = 0
  constructor(private loadingService:LoaderService) {}

  intercept(request: HttpRequest<unknown>, next: HttpHandler): Observable<HttpEvent<unknown>> {
    this.totalRequests++
    this.loadingService.setLodaing(true)
    return next.handle(request).pipe(
      finalize(()=>{
        this.totalRequests--
        if(this.totalRequests == 0){
          this.loadingService.setLodaing(false)
        }
      })
    );
  }
}
