import { HttpClient } from '@angular/common/http';
import { ResourceService } from 'src/app/core/services/resource.service';
import { Injectable } from '@angular/core';
import { OrderItem } from './models/order-item';
@Injectable({
  providedIn:'root'
})
export class OrderItemRepository extends ResourceService<OrderItem>{
  constructor(httpClient:HttpClient){
    super(httpClient)
  }
  getResourceUrl(){
    return 'order-item'
  }
}
