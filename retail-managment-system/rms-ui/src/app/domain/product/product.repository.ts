import { ResourceService } from 'src/app/core/services/resource.service';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Product } from './models/product';
import { CategoryRepository } from 'src/app/domain/category/category.repository';

@Injectable({
  providedIn: 'root',
})
export class ProductRepository extends ResourceService<Product> {
  data: Product[] = []
  constructor(
    httpClient: HttpClient,
    public categoryRepository: CategoryRepository
    ) {
    super(httpClient);
  }
  getResourceUrl(): string {
    return 'product';
  }
  filterProductsById(id:number){
    return this.categoryRepository.getListById(`${id}/product`)
  }

  toServerModel(entity: Product): any {
    return {
      id: entity.id,
      name: entity.name,
      modelNo: entity.modelNo,
      version: entity.version,
      brand: entity.brand,
      cashPrice: entity.cashPrice,
      quantity: entity.quantity,
      productCategoryDto: { id: entity.productCategoryDto.id },
    };
  }
}
