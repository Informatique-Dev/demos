import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { ResourceService } from 'src/app/core/services/resource.service';
import { Profit } from './models/profit';

@Injectable({
  providedIn: 'root'
})
export class RepositoryService extends ResourceService<Profit> {
  getResourceUrl(): string {
    return 'profits'
 }
 constructor(httpclient:HttpClient) {

   super(httpclient);
  }

}
