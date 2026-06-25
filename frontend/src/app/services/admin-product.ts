import { Injectable, inject } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { environment } from '../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class AdminProductService {

  private http = inject(HttpClient);
private api = environment.apiUrl;
  private apiUrl =
    `${this.api}/admin/products`;

  getProducts() {

    return this.http.get<any[]>(
      `${this.api}/products`
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