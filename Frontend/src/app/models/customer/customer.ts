import { ChangeDetectorRef, Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { RouterModule } from '@angular/router';

// Interface ve Service importlarını kendi dosya yoluna göre kontrol et
import { CustomerInterface } from '../../interfaces/customer-interface';
import { EmployeeInterface } from '../../interfaces/employee-interface';
import { CustomerService } from '../../services/customer-service';
import { EmployeeService } from '../../services/employee-service';

@Component({
  selector: 'app-customer',
  standalone: true, // Angular 17+ kullanıyorsan bu true kalmalı
  imports: [CommonModule, FormsModule, RouterModule],
  templateUrl: './customer.html',
  styleUrl: './customer.css',
})
export class Customer implements OnInit {
  // FORM DEĞİŞKENLERİ
  name: string = "";
  lastName: string = "";
  selectedEmployeeId: number = 0; // Dropdown'dan seçilen ID

  // LİSTELER
  public customerList: CustomerInterface[] = [];      // Tüm müşteriler
  public customerByNameList: CustomerInterface[] = []; // Arama sonuçları
  public employeeList: EmployeeInterface[] = [];       // Dropdown için çalışanlar

  // GÖSTERİM KONTROL (BAYRAKLAR)
  showAll: boolean = false;
  showSearch: boolean = false;
  searchName: string = "";

  constructor(
    private custService: CustomerService,
    private empService: EmployeeService,
    private cdr: ChangeDetectorRef
  ) {}

  ngOnInit(): void {
    this.getEmployeesForDropdown();
  }

  // 1. ÇALIŞANLARI DROPDOWN İÇİN ÇEKME
  getEmployeesForDropdown() {
    this.empService.getAllEmployees().subscribe({
      next: (res: any) => {
        this.employeeList = res;
        this.cdr.detectChanges();
      },
      error: (err: any) => {
        console.error("Çalışanlar yüklenemedi", err);
      }
    });
  }

  // 2. KAYDETME İŞLEMİ
  save() {
    // Dropdown'dan seçilen personelin adını bulalım (Backend istiyorsa)
    // Eğer backend sadece ID istiyorsa name/surname göndermene gerek yok.
    // Ama senin interface'ine uyalım:
    const selectedEmp = this.employeeList.find(e => e.id == this.selectedEmployeeId);

    const newCustomer: CustomerInterface = {
      name: this.name,
      lastName: this.lastName,
      employeeId: this.selectedEmployeeId,
      employeeName: selectedEmp ? selectedEmp.name : "",
      employeeSurname: selectedEmp ? selectedEmp.lastName : ""
    };

    this.custService.saveCustomer(newCustomer).subscribe({
      next: (response) => {
        alert("Müşteri başarıyla kaydedildi!");
        // Formu temizle
        this.name = "";
        this.lastName = "";
        this.selectedEmployeeId = 0;
        this.list(); // Kayıttan sonra listeyi yenile
      },
      error: (err) => {
        console.error("Kayıt hatası:", err);
        alert("Kayıt yapılamadı.");
      }
    });
  }

  // 3. TÜMÜNÜ LİSTELEME
  list() {
    // Önce diğer ekranları kapat
    this.showSearch = false;
    this.showAll = false;
    this.cdr.detectChanges(); // Angular'a "Arayüzü temizle" diyoruz

    this.custService.getAllCustomer().subscribe({
      next: (res: CustomerInterface[]) => {
        this.customerList = res;

        // setTimeout: O meşhur NG0100 hatasını çözer.
        // İşlemi bir sonraki döngüye atar.
        setTimeout(() => {
          this.showAll = true;
          this.cdr.detectChanges();
        }, 0);
      },
      error: (err) => {
        console.error("Listeleme hatası", err);
        alert("Listeleme yapılamadı.");
      }
    });
  }

  // 4. İSME GÖRE ARAMA
  findByName() {
    if (!this.searchName || this.searchName.trim() === "") {
      alert("Lütfen aranacak bir isim giriniz.");
      return;
    }

    // Listeyi kapat, aramayı açacağız
    this.showAll = false;
    this.showSearch = false;
    this.cdr.detectChanges();

    this.custService.getCustomersByName<CustomerInterface[]>(this.searchName).subscribe({
      next: (res: any) => {
        // Backend tek obje mi yoksa liste mi dönüyor emin olalım
        // Genelde arama sonuçları liste [] döner.
        this.customerByNameList = Array.isArray(res) ? res : [res];

        if (this.customerByNameList.length === 0) {
           alert("Bu isimde müşteri bulunamadı.");
        } else {
           setTimeout(() => {
             this.showSearch = true;
             this.cdr.detectChanges();
           }, 0);
        }
      },
      error: (err) => {
        console.error("Arama hatası", err);
        alert("Arama sırasında hata oluştu veya kayıt bulunamadı.");
        this.showSearch = false;
      }
    });
  }
}