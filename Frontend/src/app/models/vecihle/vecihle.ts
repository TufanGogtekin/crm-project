import { ChangeDetectorRef, Component } from '@angular/core';
import { VecihleInterface } from '../../interfaces/vecihle-interface';
import { VecihleService } from '../../services/vecihle-service';
import { EmployeeInterface } from '../../interfaces/employee-interface';
import { EmployeeService } from '../../services/employee-service';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { RouterModule } from '@angular/router';

@Component({
  selector: 'app-vecihle',
  imports : [CommonModule,FormsModule,RouterModule],
  templateUrl: './vecihle.html',
  styleUrl: './vecihle.css',
})
export class Vecihle implements VecihleInterface {
  plate: string = "";
  model: string = "";
  productionYear: number = 0;
  employeeId: number = 0;

  constructor(
    private vecihleSer: VecihleService,
    private empServe: EmployeeService,
    private cdr: ChangeDetectorRef) { }

  public employeeList: EmployeeInterface[] = [];
  public vecihleList: VecihleInterface[] = [];

  ngOnInit(): void {
    this.getEmployeeForDropdown();
  }

  getEmployeeForDropdown() {
    this.empServe.getAllEmployees().subscribe({
      next: (res: any) => {
        this.employeeList = res;
        this.cdr.detectChanges();
      },
      error: (err: any) => {
        console.log("çalışanlar yüklenmedi");
      }
    })
  }

  save() {
    const tempVec: VecihleInterface = {
      plate: this.plate,
      model: this.model,
      productionYear: this.productionYear,
      employeeId: this.employeeId
    }

    this.vecihleSer.saveVecihle(tempVec).subscribe({
      next: (Response) => {
        console.log("kayıt tamam");
        alert("kayıt tamam");
        this.list();
      },
      error: (err) => {
        console.log("kayıt tamam değil");
        alert("kayıt olmadı");
      }
    })
  }

  list() {
    this.vecihleSer.getAllVecihle().subscribe({
      next: (res: any) => {
        console.log("işlem başarılı");
        this.vecihleList = res;
        this.cdr.detectChanges();
      },
      error: (err: any) => {
        console.log("hata var");
        alert("hata oluştu");
      }
    })
  }

  findEmployeeName(id: number): string {
  // 1. Liste boş mu kontrolü
  if (!this.employeeList || this.employeeList.length === 0) {
    // console.log("Henüz personel listesi yüklenmedi..."); // Konsolu kirletmesin diye kapalı
    return "Yükleniyor..."; 
  }

  // 2. Gelen ID sıfır veya null mı?
  if (!id || id === 0) {
    return "Atanmamış";
  }

  // 3. Eşleşmeyi yap (Tip güvenliği için '==' kullandık, '===' değil)
  // Böylece "5" (string) ile 5 (number) eşit sayılır.
  const person = this.employeeList.find(emp => emp.id == id);

  // 4. Sonuç döndür
  if (person) {
    return person.name + " " + person.lastName;
  } else {
    // Bulamadıysa konsola ipucu bırakalım
    // console.log("Bulunamayan ID:", id, "Mevcut Liste:", this.employeeList);
    return "Bulunamadı (ID: " + id + ")";
  }
}

}
