import { ResourceService } from 'src/app/core/services/resource.service';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { customers } from './models/customers';
@Injectable({
  providedIn: 'root',
})
export class CustomersRepository extends ResourceService<customers> {
  constructor(httpClient: HttpClient) {
    super(httpClient);
  }
  getResourceUrl(): string {
    return 'customers';
  }
}
