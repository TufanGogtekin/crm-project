# 🏢 CRM Project (Müşteri İlişkileri Yönetimi)

Bu proje, Yalova Üniversitesi Bilgisayar Mühendisliği 20 günlük zorunlu stajı kapsamında geliştirilmiş Full-Stack bir CRM (Customer Relationship Management) uygulamasıdır. 

Şirketlerin müşteri, personel, araç, stok ve satış süreçlerini tek bir merkezden yönetmesini sağlayan modern ve ölçeklenebilir bir web otomasyonudur.

## 🚀 Özellikler (Modüller)

- **👥 Müşteri ve Personel Yönetimi:** Müşteri ve çalışan kayıtlarının oluşturulması, listelenmesi ve güncellenmesi.
- **🚗 Araç Yönetimi:** Sisteme araç ekleme ve bu araçları spesifik müşterilerle (Foreign Key) ilişkilendirme.
- **📦 Ürün ve Stok Yönetimi:** Ürünlerin fiyat ve stok bilgilerinin takibi.
- **📝 Görev (Task) Yönetimi:** Personel görev atamaları ve durum (Pending/Completed) takibi.
- **💼 Satış Süreci (Teklif, Sözleşme, Sipariş):** Müşteriye özel, ürün tabanlı dinamik teklif (Offer) oluşturma; onaylanan teklifleri otomatik olarak sözleşmeye (Contract) ve siparişe (Order) dönüştürme.

## 💻 Kullanılan Teknolojiler

**Backend (RESTful API):**
- Java 24
- Spring Boot (v3.4.12)
- Spring Data JPA & Hibernate
- PostgreSQL
- Maven

**Frontend (SPA):**
- Angular (TypeScript)
- Bootstrap 5 (Duyarlı UI/UX Tasarım)
- HTML5 / CSS3
- RxJS (Asenkron Veri Yönetimi)

## 📂 Proje Mimarisi (Monorepo)
Proje, Backend ve Frontend klasörlerinin tek bir depoda tutulduğu "Monorepo" yaklaşımıyla tasarlanmıştır.

```text
CRM-Project/
├── Backend/      # Spring Boot API kodları
└── Frontend/     # Angular kullanıcı arayüzü kodları