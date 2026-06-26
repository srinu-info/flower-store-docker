import { Injectable, inject } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Product } from '../models/product';
import { environment } from '../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class ProductService {

  private http = inject(HttpClient);
    private api = environment.apiUrl;

  private apiUrl = `${this.api}/products`;

  getAllProducts(): Observable<Product[]> {

  return this.http.get<Product[]>(
      `${this.apiUrl}`
  );

}
}