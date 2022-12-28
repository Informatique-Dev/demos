import { ResourceService } from 'src/app/core/services/resource.service';
import { Injectable } from '@angular/core';
import { Category } from './models/category';

@Injectable({
  providedIn: 'root',
})
export class CategoryRepository extends ResourceService<Category> {
  getResourceUrl(): string {
    return 'categories';
  }
}
