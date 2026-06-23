import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { tap } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor(
    private http: HttpClient
  ) { }

  login(data: any) {

    return this.http.post<any>(
      'http://localhost:8080/auth/login',
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
    'http://localhost:8080/auth/register',
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