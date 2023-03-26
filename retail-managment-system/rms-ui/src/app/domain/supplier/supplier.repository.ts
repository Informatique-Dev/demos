import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { ResourceService } from 'src/app/core/services/resource.service';
import { Supplier } from './models/supplier.model';
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
}
