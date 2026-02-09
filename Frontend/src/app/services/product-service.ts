import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { ProductInterface } from '../interfaces/product-interface';

@Injectable({
  providedIn: 'root',
})
export class ProductService {

  private productSavePath = "http://localhost:8080/rest/api/product/save";

  private productListPath = "http://localhost:8080/rest/api/product/list";

  constructor(private http: HttpClient) { }

  saveProduct(data: ProductInterface) {
    return this.http.post<ProductInterface>(this.productSavePath, data);
  }

  getAllProduct(){
    return this.http.get<ProductInterface[]>(this.productListPath);
  }

}
