import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { ResourceService } from 'src/app/core/services/resource.service';
import { Supplier } from './model/supplier.model';
@Injectable({
  providedIn: 'root'
})
export class SupplierRepository extends ResourceService<Supplier> {
  constructor(httpClient: HttpClient) {
    super(httpClient);
  }
  getResourceUrl(): string {
    return 'supplier';
  }
  toServerModel(entity: Supplier) {
    return {
      id: entity.id,
      version: entity.version,
      name: entity.name,
      contactName: entity.contactName,
      primarPhoneNo: entity.primarPhoneNo,
      secondaryPhoneNo: entity.secondaryPhoneNo,
      address: entity.address
    };
  }
}
