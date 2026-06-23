import { Injectable, inject } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class CartService {

  private http = inject(HttpClient);

  private apiUrl =
    'http://localhost:8080/cart';

  addToCart(payload:any){

  return this.http.post(
    `${this.apiUrl}/add`,
    payload,
    {
      responseType:'text'
    }
  );

}


  getCart(){

    return this.http.get<any>(
      this.apiUrl
    );

  }

  updateQuantity(
  cartItemId:number,
  quantity:number
){

  return this.http.put(
    `${this.apiUrl}/update`,
    {
      cartItemId,
      quantity
    },
    {
      responseType:'text'
    }
  );

}

  removeItem(id:number){

  return this.http.delete(
    `${this.apiUrl}/remove/${id}`,
    {
      responseType:'text'
    }
  );

}

}