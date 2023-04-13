import { Injectable } from '@angular/core';
import { Role } from './models/role';
import { ResourceService } from 'src/app/core/services/resource.service';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class RoleRepository extends ResourceService<Role>{

  constructor(httpClient: HttpClient) {
    super(httpClient)
   }

   getResourceUrl():string{
    return 'role'
   }
}
