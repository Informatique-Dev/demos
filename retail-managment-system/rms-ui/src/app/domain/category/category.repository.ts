import { ResourceService } from 'src/app/core/services/resource.service';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Category } from './models/category';

@Injectable({
  providedIn: 'root',
})
export class CategoryRepository extends ResourceService<Category> {
  getResourceUrl(): string {
    return 'categories';
  }
  // toServerModel(entity: Category): any {
  //   if (!entity.id) {
  //     return {
  //       name: entity.name,
  //       status: entity.status,
  //     };
  //   } else {
  //     return {
  //       name: entity.name,
  //       status: entity.status,
  //       id: entity.id,
  //     };
  //   }
  //}
}
