import { Order } from './models/order';
import { HttpClient } from '@angular/common/http';
import { ResourceService } from 'src/app/core/services/resource.service';
import { Injectable } from '@angular/core';
@Injectable({
  providedIn:'root'
})
export class OrderRepository extends ResourceService<Order>{
  constructor(httpClient:HttpClient){
    super(httpClient)
  }
  getResourceUrl(){
    return 'orders'
  }
}
