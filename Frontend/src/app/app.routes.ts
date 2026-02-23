import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

// Componentlerini import etmeyi unutma
import { Login } from './models/login/login';
import { Register } from './models/register/register';
import { Customer } from './models/customer/customer';
import { Employee } from './models/employee/employee';
import { Order } from './models/order/order';
import { Vecihle } from './models/vecihle/vecihle';
import { Offer } from './models/offer/offer';
import { Contract } from './models/contract/contract';
import { Task } from './models/task/task';
import { Activity } from './models/activity/activity';
import { Product } from './models/product/product';

export const routes: Routes = [
  // 🔥 1. KURAL: Site açılınca direkt Login'e yönlendir
  { path: '', redirectTo: 'login', pathMatch: 'full' },

  // 🔥 2. PUBLIC SAYFALAR (Herkes görebilir)
  { path: 'login', component: Login },
  { path: 'register', component: Register },

  // 🔥 3. GİZLİ SAYFALAR (Sadece giriş yapanlar)
  { path: 'employee', component: Employee },
  { path: 'customer', component: Customer },
  { path: 'vecihle', component: Vecihle },
  { path: 'product', component: Product},
  { path: 'offer', component: Offer },
  { path: 'contract', component: Contract },
  { path: 'order', component: Order },
  { path: 'task', component: Task },
  { path: 'activity', component: Activity },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }