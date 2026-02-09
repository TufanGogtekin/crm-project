import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class OfferService {

  // Backend'deki OfferController'ın adresi
  // Controller'da @RequestMapping("/rest/api/offer") demiştik.
  private apiUrl = 'http://localhost:8080/rest/api/offer';

  constructor(private http: HttpClient) { }

  // 1. Yeni Teklif Kaydet (POST)
  saveOffer(offer: any): Observable<any> {
    return this.http.post<any>(`${this.apiUrl}/save`, offer);
  }

  // 2. Tüm Teklifleri Listele (GET)
  getAllOffers(): Observable<any[]> {
    return this.http.get<any[]>(`${this.apiUrl}/list`);
  }
}