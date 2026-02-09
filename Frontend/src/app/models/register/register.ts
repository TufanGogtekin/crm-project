import { Component } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Router, RouterModule } from '@angular/router'; 
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';



@Component({
  selector: 'app-register',
  imports:[FormsModule,CommonModule,RouterModule],
  templateUrl: './register.html',
  styleUrls: ['./register.css']
})
export class Register {

  user = {
    firstName: '',
    lastName: '',
    email: '',
    password: ''
  };
  
  isLoading: boolean = false;

  constructor(
    private http: HttpClient,
    private router: Router
  ) {}

  goToLogin() {
    this.router.navigate(['/login']);
  }

  register() {
    // Veri kontrolü (Konsola bak, artık dolu gelecek!)
    console.log("🚀 Giden Veri Kontrol:", this.user); 

    // Basit Validasyon: Boşsa hiç Backend'e gitme
    if (!this.user.firstName || !this.user.lastName || !this.user.email || !this.user.password) {
        alert("Lütfen tüm alanları doldurunuz!");
        return;
    }

    this.isLoading = true;

    this.http.post('http://localhost:8080/rest/api/auth/register', this.user)
      .subscribe({
        next: (res) => {
          this.isLoading = false;
          console.log("Sunucu Cevabı:", res);
          alert("✅ Kayıt Başarılı!");
          this.goToLogin();
        },
        error: (err: any) => {
          this.isLoading = false;
          console.error("Hata:", err);
          // Eğer 500 hatası geliyorsa email çakışıyordur
          alert("Kayıt başarısız! (Bu e-posta kullanılıyor olabilir)");
        }
      });
  }
}