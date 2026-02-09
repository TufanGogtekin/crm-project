import { ApplicationConfig } from '@angular/core';
import { provideRouter } from '@angular/router';
import { provideHttpClient } from '@angular/common/http'; // Backend için şart

import { routes } from './app.routes'; // 1. adımdaki dosyayı import et

export const appConfig: ApplicationConfig = {
  providers: [
    provideRouter(routes),     // 🔥 Rotaları sisteme yükle
    provideHttpClient()        // 🔥 Backend istekleri için bunu ekle
  ]
};