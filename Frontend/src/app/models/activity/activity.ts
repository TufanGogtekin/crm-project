import { ChangeDetectorRef, Component, OnInit } from '@angular/core';
import { ActivityInterface } from '../../interfaces/activity-interface';
import { ActivityService } from '../../services/activity-service';
import { CustomerService } from '../../services/customer-service';
import { EmployeeService } from '../../services/employee-service';
import { CustomerInterface } from '../../interfaces/customer-interface';
import { EmployeeInterface } from '../../interfaces/employee-interface';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-activity',
  imports : [CommonModule,FormsModule],
  templateUrl: './activity.html',
  styleUrl: './activity.css',
})
export class Activity implements OnInit, ActivityInterface {
  // Arayüz Değişkenleri (HTML ile bağlı)
  subject: string = "";
  type: string = "";       // CALL, MEETING, ONLINE, EMAIL, VISIT
  date: string = "";       // "2026-01-29" formatında gelir
  hour: string = "";       // "14:30" formatında gelir
  duration: number = 60;   // Varsayılan 60 dk
  status: string = "PLANNED"; 
  description: string = "";
  
  customerId: number = 0;
  employeeId: number = 0;

  // Listeler
  activityList: ActivityInterface[] = [];
  customerList: CustomerInterface[] = [];
  employeeList: EmployeeInterface[] = [];

  constructor(
    private activityService: ActivityService,
    private customerService: CustomerService,
    private employeeService: EmployeeService,
    private cdr: ChangeDetectorRef
  ) {}

  ngOnInit(): void {
    // Sayfa açılınca dropdownları ve mevcut listeyi doldur
    this.getAllCustomers();
    this.getAllEmployees();
  }

  // --- 1. DROPDOWN VERİLERİNİ ÇEKME ---
  getAllCustomers() {
    this.customerService.getAllCustomer().subscribe({
      next: (res: any) => {
        this.customerList = res;
      }
    });
  }

  getAllEmployees() {
    this.employeeService.getAllEmployees().subscribe({
      next: (res: any) => {
        this.employeeList = res;
      }
    });
  }

  save() {
    if (!this.subject || !this.date || !this.type) {
      alert("Lütfen Konu, Tarih ve Aktivite Tipini doldurunuz!");
      return;
    }

    const tempActivity: ActivityInterface = {
      subject: this.subject,
      type: this.type,
      date: this.date,
      hour: this.hour,
      duration: this.duration,
      status: this.status,
      description: this.description,
      customerId: this.customerId,
      employeeId: this.employeeId
    };

    console.log("Gönderilen Veri:", tempActivity);

    this.activityService.saveActivity(tempActivity).subscribe({
      next: (res) => {
        this.islemBasarili();
      },
      error: (err) => {
        if (err.status === 200) {
          this.islemBasarili();
        } else {
          console.error("Hata:", err);
          alert("Kayıt sırasında bir hata oluştu!");
        }
      }
    });
  }

  list() {
    this.activityService.getAllActivities().subscribe({
      next: (res: any) => {
        console.log("Aktiviteler Geldi:", res);
        this.activityList = res;
        this.cdr.detectChanges(); 
      },
      error: (err) => {
        console.error("Liste çekilemedi:", err);
      }
    });
  }

  islemBasarili() {
    alert("Aktivite Planlandı! 📅");
    this.list();      
    this.clearForm();
  }

  clearForm() {
    this.subject = "";
    this.description = "";
    this.customerId = 0;
  }
}