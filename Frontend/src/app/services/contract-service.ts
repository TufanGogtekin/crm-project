import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ContractService {

  private apiUrl = 'http://localhost:8080/rest/api/contract';

  constructor(private http: HttpClient) { }

  convertOfferToContract(offerId: number): Observable<any> {
    return this.http.post<any>(`${this.apiUrl}/convert/${offerId}`, {});
  }

  getAllContracts(): Observable<any[]> {
    return this.http.get<any[]>(`${this.apiUrl}/list`);
  }
}