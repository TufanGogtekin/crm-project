import { ChangeDetectorRef, Component, OnInit } from '@angular/core';
import { CustomerInterface } from '../../interfaces/customer-interface';
import { CustomerService } from '../../services/customer-service';
import { EmployeeService } from '../../services/employee-service';
import { EmployeeInterface } from '../../interfaces/employee-interface';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { RouterModule } from '@angular/router';

@Component({
  selector: 'app-customer',
  imports : [CommonModule,FormsModule,RouterModule],
  templateUrl: './customer.html',
  styleUrl: './customer.css',
})
export class Customer implements CustomerInterface, OnInit {
  name: string = "";
  lastName: string = "";
  employeeId: number = 0;
  employeeName: string = "";
  employeeSurname: string = "";

  constructor(
    private custService: CustomerService,
    private cdr: ChangeDetectorRef,
    private empService: EmployeeService) { }
  id?: number | undefined;
  

  public customerList: CustomerInterface[] = [];

  public employeeList: EmployeeInterface[] = [];

  public customerByName: CustomerInterface[] = [];

  ngOnInit(): void {
    this.getEmployeesForDropdown();
  }

  getEmployeesForDropdown() {
    this.empService.getAllEmployees().subscribe({
      next: (res: any) => { // Hata almamak için any diyebilirsin veya EmployeeInterface[]
        this.employeeList = res;
        this.cdr.detectChanges();
      },
      error: (err: any) => {
        console.log("Çalışanlar yüklenemedi", err);
      }
    });
  }

  save() {

    const newCustomer: CustomerInterface = {
      name: this.name,
      lastName: this.lastName,
      employeeId: this.employeeId,
      employeeName:this.employeeName,
      employeeSurname:this.employeeSurname
    }

    this.custService.saveCustomer(newCustomer).subscribe({
      next: (response) => {
        console.log("gitti ve cevap geldi", response);
        alert("kayıt başarılı");
      },
      error: (err) => {
        console.error("hata oluşştu", err);
      }
    })
  }

  list() {

    this.show = false;
    
    this.custService.getAllCustomer().subscribe({
      next: (Response) => {
        console.log("listeleme başarılı");
        this.customerList = Response;
        this.cdr.detectChanges();
        this.show = true;
      },
      error: (err) => {
        console.log("hata var listeleme yapılamadı");
        alert("işlem hatası: listeleme yapılamadı");
      }
    })

  }

  show: boolean = false;
  searchName: string = "";
  findByName() {

    this.show = true;
    
    this.custService.getCustomersByName<CustomerInterface>(this.searchName).subscribe({
      next: (res: any) => {
        console.log("işlem tamam");
        this.customerByName = res;
        this.cdr.detectChanges();
        this.show = true;
      },
      error : (err: any) => {
        console.log("hata var");
        alert("işlem başarısız");
        this.show  = false;
    }
    })
    
}

}
