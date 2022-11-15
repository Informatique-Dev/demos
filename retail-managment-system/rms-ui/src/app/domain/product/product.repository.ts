import { ResourceService } from 'src/app/core/services/resource.service';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Product } from './models/product';
@Injectable({
  providedIn: 'root',
})
export class ProductRepository extends ResourceService<Product> {
  constructor(httpClient: HttpClient) {
    super(httpClient);
  }
  getResourceUrl(): string {
    return 'products';
  }

  toServerModel(entity: Product): any {
    if (!entity.id) {
      return {
        id: entity.id,
        name: entity.name,
      };
    } else {
      return {
        id: entity.id,
        name: entity.name,
        modelNo: entity.modelNo,
        version: entity.version,
        brand: entity.brand,
        cashPrice: entity.cashPrice,
        quantity: entity.quantity,
        productCategoryDto: entity.productCategoryDto,
      };
    }
  }
}
