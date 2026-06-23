import { Injectable, inject } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class AdminProductService {

  private http = inject(HttpClient);

  private apiUrl =
    'http://localhost:8080/admin/products';

  getProducts() {

    return this.http.get<any[]>(
      'http://localhost:8080/products'
    );

  }

  addProduct(product:any) {

  return this.http.post(
    this.apiUrl,
    product,
    {
      observe: 'response'
    }
  );

}

  updateProduct(
    id:number,
    product:any
  ) {

    return this.http.put(
      `${this.apiUrl}/${id}`,
      product
    );

  }

  deleteProduct(id:number) {

    return this.http.delete(
      `${this.apiUrl}/${id}`,
      {
        responseType:'text'
      }
    );

  }

}