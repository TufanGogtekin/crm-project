import { ChangeDetectorRef, Component } from '@angular/core';
import { TaskInterface } from '../../interfaces/task-interface';
import { TaskService } from '../../services/task-service';
import { CustomerService } from '../../services/customer-service';
import { EmployeeService } from '../../services/employee-service';
import { CustomerInterface } from '../../interfaces/customer-interface';
import { EmployeeInterface } from '../../interfaces/employee-interface';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-task',
  imports : [CommonModule,FormsModule],
  templateUrl: './task.html',
  styleUrl: './task.css',
})
export class Task implements TaskInterface {
  title: string ="";
  description: string = "";
  dueDate: Date|null = null ;
  status: string = "";
  customerId: number = 0;
  employeeId: number = 0;
  customerInfo: string = "";
  employeeInfo: string = ""; 

  public customerList: CustomerInterface[] = [];
  public employeeList: EmployeeInterface[] = [];

  constructor(
    private taskServe:TaskService,
    private customerService:CustomerService,
    private empService:EmployeeService,
    private cdr:ChangeDetectorRef){}

    ngOnInit(): void {
    this.getEmployeesForDropdown();
    this.getCustomerForDropdown();
    this.list();
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


    getCustomerForDropdown(){
      this.customerService.getAllCustomer().subscribe({
        next: (res:any) => {
          this.customerList = res;
          this.cdr.detectChanges();
        },
        error: (err:any) => {
          console.log("müşteriler yüklenmedi");
        }
      })
    }

  save(){

    const tempTask:TaskInterface = {
      title: this.title,
      description : this.description,
      dueDate : this.dueDate,
      status : this.status,
      customerId: this.customerId,
      employeeId: this.employeeId,

    }

    this.taskServe.saveTask(tempTask).subscribe({
        next: (Response)  => {
          console.log("kayıt tamam",Response);
          alert("kayıt tamam");
          this.list();
        },
        error: (err) => {
          console.log("kayıt tamam değil",err);
          alert("kayıt tamam değil");
        }
    })
  }
  public taskList: any[] = [];
  list(){

    this.taskServe.getAllTask().subscribe({
      next: (res:any) => {
        console.log("işlem tamam", res);
        this.taskList = res;
        this.cdr.detectChanges();
      },
      error: (err:any) => {
        console.log("hata var",err);
        alert("liste görüntülenemiyor");
      }
    })
  }

  getStatusTR(status: string): string {
    if (status === 'IN_PROGRESS') return 'Devam Ediyor';
    if (status === 'COMPLETED') return 'Tamamlandı';
    if (status === 'NEW') return 'Yeni';
    if (status === 'CANCELLED') return 'İptal';
    return status; // Tanınmayan bir şey gelirse olduğu gibi göster
}
}
