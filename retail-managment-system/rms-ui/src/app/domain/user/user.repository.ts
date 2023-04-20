import { Injectable } from '@angular/core';
import { ResourceService } from 'src/app/core/services/resource.service';
import { User } from './models/user';
import { HttpClient } from '@angular/common/http';
import { catchError, Observable } from 'rxjs';
import { Role } from '../role/models/role';


@Injectable({
  providedIn: 'root'
})
export class UserRepository extends ResourceService<User>{

  constructor(httpClient: HttpClient) {
    super(httpClient)
   }

   getResourceUrl(): string {
     return 'user'
   }

   assignRoleToServerModel(entity):any{
    return {
      id: entity.role.id
    }
   }
   assignRoleToUser(resource:Role,id:number):Observable<any>{
      return this.httpClient
        .post(`${this.APIUrl}/${id}/role`, this.assignRoleToServerModel(resource))
        .pipe(
          catchError((err) => {
            throw new Error(err.message);
          })
        );
    }

    deleteRoleOfUser(id: string | number): Observable<any> {
      return this.httpClient.delete(`${this.APIUrl}-role/${id}`).pipe(
        catchError((err) => {
          throw new Error(err.message);
        })
      );
    }
}
