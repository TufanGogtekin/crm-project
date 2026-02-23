import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { VecihleInterface } from '../interfaces/vecihle-interface';

@Injectable({
  providedIn: 'root',
})
export class VecihleService {

  private vecihlePath = "http://localhost:8080/rest/api/vecihle";

  constructor(private http: HttpClient) { }

  saveVecihle(data: VecihleInterface) {
    return this.http.post<VecihleInterface>(`${this.vecihlePath}/save`, data);
  }

  getAllVecihle() {
    return this.http.get<VecihleInterface>(`${this.vecihlePath}/list`);
  }

}