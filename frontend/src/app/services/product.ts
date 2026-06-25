import { Injectable, inject } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Product } from '../models/product';

@Injectable({
  providedIn: 'root'
})
export class ProductService {

  private http = inject(HttpClient);

  private apiUrl = 'http://100.31.238.92:8080/products';

  getAllProducts(): Observable<Product[]> {

  return this.http.get<Product[]>(
      `${this.apiUrl}`
  );

}
}