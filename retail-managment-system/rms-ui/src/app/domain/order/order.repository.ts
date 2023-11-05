import { ResourceService } from 'src/app/core/services/resource.service';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Order } from './models/order';

@Injectable({
  providedIn: 'root',
})
export class OrderRepository extends ResourceService<Order> {
  constructor(httpClient: HttpClient) {
    super(httpClient);
  }
  getResourceUrl(): string {
    return 'order';
  }
  toServerModel(entity: Order): any {
    console.log(entity);
    return {
      id: entity.id,
      orderDate: entity.orderDate,
      paidAmount: entity.paidAmount,
      remainingAmount: entity.remainingAmount,
      paymentType:entity.paymentType,
      totalPrice: entity.totalPrice,
      employee: { id: entity.employee.id },
      customer: { id: entity.customer.id },
      orderItems:entity.orderItems
    };
  }
}
