import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';


@Injectable({
  providedIn: 'root',
})
export class ProductService {

  private productsBaseApiUrl = 'http://localhost:8080/products/api';

  constructor(private http: HttpClient) {}

  findAllProducts(): Observable<any> {
    return this.http.get(`${this.productsBaseApiUrl}/user/findAllProducts`);
  }

  
}
