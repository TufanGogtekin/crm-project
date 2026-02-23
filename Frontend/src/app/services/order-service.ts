import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class OrderService {

  private orderPath = 'http://localhost:8080/rest/api/order'; 

  constructor(private http: HttpClient) { }

  saveOrder(order: any): Observable<any> {
    return this.http.post<any>(`${this.orderPath}/save`, order);
  }

  getAllOrders(): Observable<any[]> {
    return this.http.get<any[]>(`${this.orderPath}/list`);
  }
}