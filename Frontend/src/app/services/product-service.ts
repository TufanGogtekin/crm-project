import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { ProductInterface } from '../interfaces/product-interface';

@Injectable({
  providedIn: 'root',
})
export class ProductService {

  private productPath = "http://localhost:8080/rest/api/product";

  constructor(private http: HttpClient) { }

  saveProduct(data: ProductInterface) {
    return this.http.post<ProductInterface>(`${this.productPath}/save`, data);
  }

  getAllProduct(){
    return this.http.get<ProductInterface[]>(`${this.productPath}/list`);
  }

}
