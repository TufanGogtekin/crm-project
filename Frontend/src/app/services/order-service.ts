import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class OrderService {

  // Senin backend portuna göre ayarla
  private apiUrl = 'http://localhost:8080/rest/api/order'; 

  constructor(private http: HttpClient) { }

  // Sipariş Kaydet
  saveOrder(order: any): Observable<any> {
    return this.http.post<any>(`${this.apiUrl}/save`, order);
  }

  // Tüm Siparişleri Getir
  getAllOrders(): Observable<any[]> {
    return this.http.get<any[]>(`${this.apiUrl}/list`);
  }
}