import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { EmployeeInterface } from '../interfaces/employee-interface';

@Injectable({
  providedIn: 'root',
})
export class EmployeeService {

  private saveEmployee_path = "http://localhost:8080/rest/api/employee/save";

  private getAllEmployees_path = "http://localhost:8080/rest/api/employee/list";

  private getEmployeeByName_path = "http://localhost:8080/rest/api/employee/find/name/";

  private getEmployeeById_path = "http://localhost:8080/rest/api/employee/find/Id/";

  constructor(private http: HttpClient) { }

  saveEmployee(data: EmployeeInterface) {
    return this.http.post(this.saveEmployee_path, data);
  }

  getAllEmployees() {
    return this.http.get(this.getAllEmployees_path);
  }


  getEmployeeById(id: number) {
    let byFindId: string = this.getEmployeeById_path + id;
    return this.http.get<EmployeeInterface>(byFindId);
  }

  getEmployeeByName(name:string){
    let byFindName: string = this.getEmployeeByName_path + name;
    return this.http.get<EmployeeInterface[]>(byFindName);
  }

}
