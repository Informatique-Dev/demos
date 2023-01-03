
import {ResourcesService } from 'src/app/core/services/resources.service';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Profit } from './model/test';
@Injectable({
  providedIn: 'root'
})
export class RepositoryService extends ResourcesService<Profit> {
  getResourcesUrl(): string {
    return 'profits'
  }

 constructor(httpclient:HttpClient) {
   super(httpclient);
  }

}
