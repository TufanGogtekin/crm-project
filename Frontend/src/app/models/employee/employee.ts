import { ChangeDetectorRef, Component } from '@angular/core';
import { EmployeeInterface } from '../../interfaces/employee-interface';
import { EmployeeService } from '../../services/employee-service';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-employee',
  imports : [CommonModule,FormsModule],
  templateUrl: './employee.html',
  styleUrl: './employee.css',
})
export class Employee implements EmployeeInterface {
  name: string = "";
  lastName: string = "";

  showList: boolean = false;
  show: boolean = false;

  employeeList: EmployeeInterface[] = [];
  searchResults: EmployeeInterface[] = [];
  employeeById: EmployeeInterface = {
    name: "",
    lastName: ""
  };

  constructor(private empService: EmployeeService , private cdr: ChangeDetectorRef) {}

  islemBasarili() {
    alert("Kayıt Başarılı!"); 
    this.list();         
    this.name = "";       
    this.lastName = "";   
  }

  save() {

    const newEmployee: EmployeeInterface = {
      name: this.name,
      lastName: this.lastName
    };

    this.empService.saveEmployee(newEmployee).subscribe({
      next: (response) => {
        console.log("Gitti ve cevap geldi:", response);
      },
      error: (err) => {
        console.error("Hata oldu:", err);
        if (err.status === 200) {
           console.log("Gizli Başarı: Text formatında cevap geldi.");
           this.islemBasarili();
        } else {
           alert("Bir hata oluştu. Lütfen konsolu kontrol ediniz.");
        }
      }
    });

    

    this.empService.getAllEmployees().subscribe({
      next: (response) => {
        console.log("Gitti ve cevap geldi:", response);
      },
      error: (err) => {
        console.error("Hata oldu:", err);
      }
    });



  }


  list() {
    this.showList = true;
    this.cdr.detectChanges();

    this.empService.getAllEmployees().subscribe({
      next: (response: any) => {
        console.log("Liste Geldi:", response);
        this.employeeList = response;
        this.cdr.detectChanges();
      },
      error: (err) => {
        console.error("Liste gelmedi:", err);
      }
    });

  }

  searchName: string | null = null;

  findByName() {
    this.show = false;
    this.cdr.detectChanges();

    if (!this.searchName) {
      alert("Lütfen geçerli bir name giriniz!");
      return;
    }

    this.empService.getEmployeeByName(this.searchName).subscribe({
      next: (response: EmployeeInterface[]) => {
        console.log("Bulundu:", response);
        if (Array.isArray(response) && response.length > 0) {
            this.searchResults = response;
            this.show = true;
            this.cdr.detectChanges();
        } 
        else {
             alert("Bu isim ile kayıtlı çalışan bulunamadı!");
             this.show = false;
             this.cdr.detectChanges();
        }
      },
      error: (err) => {
        console.error("Hata:", err);
        alert("Bu isim ile kayıtlı çalışan bulunamadı!");
        this.show = false;
        this.cdr.detectChanges();
      }
    });
  }

  
}
