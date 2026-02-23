import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { EmployeeInterface } from '../interfaces/employee-interface';

@Injectable({
  providedIn: 'root',
})
export class EmployeeService {

  private employeePath = "http://localhost:8080/rest/api/employee";

  constructor(private http: HttpClient) { }

  saveEmployee(data: EmployeeInterface) {
    return this.http.post(`${this.employeePath}/save`, data);
  }

  getAllEmployees() {
    return this.http.get(`${this.employeePath}/list`);
  }

  getEmployeeById(id: number) {
    return this.http.get<EmployeeInterface>(`${this.employeePath}/find/${id}`);
  }

  getEmployeeByName(name:string){
    return this.http.get<EmployeeInterface[]>(`${this.employeePath}/find/name/${name}`);
  }

}
