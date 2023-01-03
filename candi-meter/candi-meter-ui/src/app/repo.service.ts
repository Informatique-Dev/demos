import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { ResourceService } from './core/services/resource.service';
import { Profit } from './profit';

@Injectable({
  providedIn: 'root'
})
export class RepoService extends ResourceService<Profit> {

  getResourceUrl(): string {
    return 'profits'
 }
 constructor(httpclient:HttpClient) {

   super(httpclient);
  }

}
