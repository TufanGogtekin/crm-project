import { Component, OnInit, ChangeDetectorRef } from '@angular/core'; // 1. ChangeDetectorRef EKLENDİ
import { ContractService } from '../../services/contract-service';
import { finalize } from 'rxjs/operators'; // 2. finalize EKLENDİ
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-contract',
  imports : [CommonModule,FormsModule],
  templateUrl: './contract.html',
  styleUrls: ['./contract.css']
})
export class Contract implements OnInit {

  contractList: any[] = [];
  isLoading: boolean = false; 

  // 3. Constructor'a 'private cdr: ChangeDetectorRef' EKLENDİ
  constructor(
    private contractService: ContractService,
    private cdr: ChangeDetectorRef 
  ) { }

  ngOnInit(): void {
    this.getAllContracts();
  }

  getAllContracts() {
    this.isLoading = true;
    console.log("⏳ Sözleşmeler isteniyor...");

    this.contractService.getAllContracts()
      .pipe(
        // 4. finalize: İşlem (başarılı veya hatalı) bittiği an BURASI ÇALIŞIR
        finalize(() => {
          this.isLoading = false; 
          this.cdr.detectChanges(); // 🔥🔥🔥 EKRANI ZORLA GÜNCELLE!
          console.log("🏁 İşlem Bitti. Spinner kapatıldı.");
        })
      )
      .subscribe({
        next: (res) => {
          console.log("✅ Veri Geldi:", res);
          this.contractList = res;
        },
        error: (err) => {
          console.error("❌ Hata:", err);
        }
      });
  }
}