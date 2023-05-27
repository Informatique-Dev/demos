import { ResourceService } from 'src/app/core/services/resource.service';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Product } from './models/product';
import { CategoryRepository } from 'src/app/domain/category/category.repository';
import { SettingsService } from 'src/app/core/services/settings.service';
import { Observable } from 'rxjs';
import { Response } from 'src/app/core/models/response';



@Injectable({
  providedIn: 'root',
})
export class ProductRepository extends ResourceService<Product> {
  
protected readonly productCategoryUrl =
SettingsService.configurationEnvironment.api.baseUrl
  constructor(
    httpClient: HttpClient,
    public categoryRepository: CategoryRepository
    ) {
    super(httpClient);
  }
  getResourceUrl(): string {
    return 'product';
  }
  filterProductsById(id:number): Observable<Response<Product>>{
    return this.httpClient.get<Response<Product>>(`${this.productCategoryUrl}category/${id}/product`)
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
