import { Injectable } from '@angular/core';
import { Store } from './models/store';
import { ResourceService } from 'src/app/core/services/resource.service';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Response } from '../../core/models/response';

@Injectable({
  providedIn: 'root'
})
export class storeRepository extends ResourceService<Store> {

  constructor(httpclient:HttpClient) {
    super(httpclient)
   }

   getResourceUrl(): string {
    return 'store'
 }

 filterStoreByEmployee(page:number,size:number,responsible:string):Observable<Response<Store>>{
  return this.getList({page,size,responsible})
 }

}
