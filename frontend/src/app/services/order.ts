import { Injectable, inject } from '@angular/core';
import {
  HttpClient,
  HttpHeaders
} from '@angular/common/http';

@Injectable({
  providedIn:'root'
})
export class OrderService {

  private http = inject(HttpClient);

  private apiUrl =
    'http://localhost:8080/orders';

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