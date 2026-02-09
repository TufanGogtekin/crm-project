import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { CustomerInterface } from '../interfaces/customer-interface';

@Injectable({
  providedIn: 'root',
})
export class CustomerService {

  private SavePath = "http://localhost:8080/rest/api/customer/save";
  private listAllPath = "http://localhost:8080/rest/api/customer/list"
  private findByNamePath = "http://localhost:8080/rest/api/customer/find/name/"

  constructor(private http: HttpClient) { }

  saveCustomer(data: CustomerInterface) {
    return this.http.post(this.SavePath, data);
  }

  getAllCustomer() {
    return this.http.get<CustomerInterface[]>(this.listAllPath);
  }

  getCustomersByName<CustomerInterface>(name:string){
    let path = this.findByNamePath + name;
    return this.http.get<CustomerInterface>(path);
  }

}
