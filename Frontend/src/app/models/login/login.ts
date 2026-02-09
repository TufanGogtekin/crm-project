import { Component } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Router, RouterModule } from '@angular/router';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-login',
  imports:[CommonModule,RouterModule,FormsModule],
  templateUrl: './login.html',
  styleUrls: ['./login.css']
})
export class Login {

  email: string = '';
  password: string = '';
  isLoading: boolean = false; // Butona basınca yükleniyor dönsün

  constructor(
    private http: HttpClient,
    private router: Router
  ) {}

  login() {
    // 1. Basit doğrulama: Alanlar boş mu?
    if (!this.email || !this.password) {
      alert("Lütfen e-posta ve şifrenizi giriniz!");
      return;
    }

    this.isLoading = true; // Yükleme başladı

    // 2. Backend'e gönderilecek paket
    const loginRequest = {
      email: this.email,
      password: this.password
    };

    // 3. İstek atılıyor
    this.http.post('http://localhost:8080/rest/api/auth/login', loginRequest)
      .subscribe({
        next: (res: any) => {
          this.isLoading = false;
          
          if (res) {
            // ✅ BAŞARILI: Kullanıcı bulundu
            console.log("Giriş Başarılı:", res);
            
            // Kullanıcıyı tarayıcı hafızasına (Local Storage) kaydet
            localStorage.setItem('user', JSON.stringify(res));

            // Müşteriler sayfasına yönlendir (Ana sayfan neresiyse orası)
            this.router.navigate(['/customer']);
          } else {
            // ❌ BAŞARISIZ: Kullanıcı null döndü (Şifre yanlış olabilir)
            alert("E-posta veya şifre hatalı!");
          }
        },
        error: (err) => {
          this.isLoading = false;
          console.error("Giriş Hatası:", err);
          alert("Sunucuya bağlanılamadı! Backend açık mı?");
        }
      });
  }
}