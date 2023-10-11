import { ResourceService } from 'src/app/core/services/resource.service';
import { Injectable } from '@angular/core';
import { User } from './models/user';

@Injectable({
  providedIn: 'root',
})
export class LoginRepository extends ResourceService<User> {
  getResourceUrl(): string {
    return 'auth/login';
  }
}