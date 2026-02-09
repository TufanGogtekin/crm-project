import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { VecihleInterface } from '../interfaces/vecihle-interface';

@Injectable({
  providedIn: 'root',
})
export class VecihleService {

  constructor(
    private http: HttpClient) { }

  private vecihleSavePath = "http://localhost:8080/rest/api/vecihle/save";

  private vecihleGetAllPath = "http://localhost:8080/rest/api/vecihle/list";

  saveVecihle(data: VecihleInterface) {
    return this.http.post<VecihleInterface>(this.vecihleSavePath, data);
  }

  getAllVecihle(){
    return this.http.get<VecihleInterface>(this.vecihleGetAllPath);
  }
}
