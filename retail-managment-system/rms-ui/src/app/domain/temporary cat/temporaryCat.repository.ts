import { ResourceService } from 'src/app/core/services/resource.service';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { temporaryCat } from './models/temporary-cat';
@Injectable({
  providedIn: 'root',
})
export class temporaryCatRepository extends ResourceService<temporaryCat> {
  constructor(httpClient: HttpClient) {
    super(httpClient);
  }
  getResourceUrl(): string {
    return 'categories';
  }
}
