import { Injectable } from '@angular/core';
@Injectable({
  providedIn: 'root',
})
export class AuthService {
  setToken(): void {
    return localStorage.setItem('token', 'any value');
  }

  getToken() {
    return localStorage.getItem('token');
  }
}
