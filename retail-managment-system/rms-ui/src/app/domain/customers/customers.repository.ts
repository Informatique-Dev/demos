import { ResourceService } from 'src/app/core/services/resource.service';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Customers } from './models/customers';
@Injectable({
  providedIn: 'root',
})
export class CustomersRepository extends ResourceService<Customers> {
  constructor(httpClient: HttpClient) {
    super(httpClient);
  }
  getResourceUrl(): string {
    return 'customer';
  }
}
