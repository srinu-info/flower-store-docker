import { Injectable, inject } from '@angular/core';
import {
  HttpClient,
  HttpHeaders
} from '@angular/common/http';
import { environment } from '../../environments/environment';

@Injectable({
  providedIn:'root'
})
export class OrderService {

  private http = inject(HttpClient);
  private api = environment.apiUrl;

  private apiUrl =
    `${this.api}/orders`;

  placeOrder(payload:any){

    const token =
      localStorage.getItem('token');

    const headers =
      new HttpHeaders({

        Authorization:
          `Bearer ${token}`

      });

    return this.http.post(
      `${this.apiUrl}/place`,
      payload,
      {
        headers,
        responseType:'text'
      }
    );

  }

  getOrders(){

    return this.http.get<any[]>(
      this.apiUrl
    );

  }

}