import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { Router, RouterModule, RouterOutlet } from '@angular/router';

@Component({
  selector: 'app-root',
  imports: [CommonModule, RouterOutlet, RouterModule],
  templateUrl: './app.html',
  styleUrls: ['./app.css']
})
export class AppComponent {
  
  constructor(public router: Router) {}

  // 🔥 Navbar Görünsün mü? (Login ve Register hariç her yerde Evet)
  isShowLayout(): boolean {
    const currentUrl = this.router.url;
    
    // Eğer adres bunlardan biriyse FALSE döndür (Gizle)
    if (currentUrl === '/login' || currentUrl === '/register' || currentUrl === '/') {
      return false;
    }
    
    // Diğer sayfalarda (Customer, Order vs.) TRUE döndür (Göster)
    return true;
  }

  // Çıkış Yap Butonu İçin
  logout() {
    localStorage.removeItem('user'); // Giriş bilgisini sil
    this.router.navigate(['/login']); // Login sayfasına at
  }
}