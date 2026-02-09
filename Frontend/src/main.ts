import { bootstrapApplication } from '@angular/platform-browser';
import { appConfig } from './app/app.config';
// HATA BURADA: 'App' yazıyorsa sil, 'AppComponent' yap.
import { AppComponent } from './app/app'; 

bootstrapApplication(AppComponent, appConfig) // Burayı da AppComponent yap
  .catch((err) => console.error(err));