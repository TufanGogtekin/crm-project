import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class OfferService {

  private offerPath = 'http://localhost:8080/rest/api/offer';

  constructor(private http: HttpClient) { }

  saveOffer(offer: any): Observable<any> {
    return this.http.post<any>(`${this.offerPath}/save`, offer);
  }

  getAllOffers(): Observable<any[]> {
    return this.http.get<any[]>(`${this.offerPath}/list`);
  }
}