import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { tap } from 'rxjs/operators';
import { environment } from '../../environments/environment';
//import { environment } from '../../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
 private api = environment.apiUrl;
  constructor(
    private http: HttpClient
  ) { }

  login(data: any) {

    return this.http.post<any>(
      `${this.api}/auth/login`,
      data
    ).pipe(
      tap(response => {

        localStorage.setItem(
          'token',
          response.token
        );

        localStorage.setItem(
          'email',
          response.email
        );

      })
    );

  }

  register(data:any){

  return this.http.post(
      `${this.api}/auth/register`,
    data,
    {
      responseType: 'text'
    }
  );

}

  getToken(): string | null {

    return localStorage.getItem('token');

  }

  logout() {

    localStorage.removeItem('token');
    localStorage.removeItem('email');

  }

  isLoggedIn(): boolean {

    return !!localStorage.getItem('token');

  }

}