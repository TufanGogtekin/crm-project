import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { OrderItemInterface } from '../interfaces/order-item-interface';

@Injectable({
  providedIn: 'root',
})
export class OrderItemService {
  constructor(private http: HttpClient) { }

  private orderItemSavePath = "http://localhost:8080/rest/api/orderitem/save";

  saveOrderItem(data: OrderItemInterface) {
    return this.http.post<OrderItemInterface>(this.orderItemSavePath, data);
  }
}
