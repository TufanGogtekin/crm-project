import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { CustomerInterface } from '../interfaces/customer-interface';

@Injectable({
  providedIn: 'root',
})
export class CustomerService {

  private customerPath = "http://localhost:8080/rest/api/customer";

  constructor(private http: HttpClient) { }

  saveCustomer(data: CustomerInterface) {
    return this.http.post(`${this.customerPath}/save`, data);
  }

  getAllCustomer() {
    return this.http.get<CustomerInterface[]>(`${this.customerPath}/list`);
  }

  getCustomersByName<CustomerInterface>(name: string) {
    return this.http.get<CustomerInterface>(`${this.customerPath}/find/name/${name}`);
  }

}
