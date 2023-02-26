import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { ResourceService } from 'src/app/core/services/resource.service';
import { Installment } from './models/installment';

@Injectable({
  providedIn: 'root'
})
export class InstallmentRepositry extends ResourceService<Installment> {
  
  constructor(httpClient: HttpClient) {
    super(httpClient);       
  }
  getResourceUrl(): string {
    return 'installments';
  }

  toServerModel(entity: Installment): any {
    return {
      id: entity.id,
      installmentAmount: entity.installmentAmount,
      paymentAmount: entity.paymentAmount,
      dueDate: entity.dueDate,
      paymentDate: entity.paymentDate,
      status: entity.status,
    };
  }

 
}

 
