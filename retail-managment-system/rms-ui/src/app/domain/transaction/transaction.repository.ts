import { ResourceService } from 'src/app/core/services/resource.service';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import {Transaction} from './models/transaction'
@Injectable({
  providedIn: 'root',
})
export class TransactionRepository extends ResourceService<Transaction> {
  constructor(httpClient: HttpClient) {
    super(httpClient);
  }
  getResourceUrl(): string {
    return 'transaction';
  }

  toServerModel(entity: Transaction): any {
    return {
        id: entity.id,
        version: entity.version,
        transactionType: entity.transactionType,
        amount: entity.amount,
        date: entity.date,
        //investorDto:{
            //id: entity.investorDto.id,
        //}
    };
  }
}
