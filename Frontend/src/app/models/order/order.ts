import { Component, OnInit, ChangeDetectorRef } from '@angular/core'; // 🔥 1. ChangeDetectorRef EKLENDİ
import { OrderService } from '../../services/order-service';
import { CustomerService } from '../../services/customer-service';
import { ProductService } from '../../services/product-service';
import { EmployeeService } from '../../services/employee-service';
import { finalize } from 'rxjs/operators'; // 🔥 2. finalize EKLENDİ
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-order',
  imports : [CommonModule,FormsModule],
  templateUrl: './order.html',
  styleUrls: ['./order.css']
})
export class Order implements OnInit {

  // --- SİPARİŞ NESNESİ ---
  order: any = {
    customerId: 0,
    employeeId: 0,
    code: '',
    status: 'PENDING',
    orderDate: '', 
    totalAmount: 0,
    items: [] 
  };

  // Listeler
  customerList: any[] = [];
  productList: any[] = [];
  employeeList: any[] = [];
  orderList: any[] = []; 

  grandTotal: number = 0;
  isLoading: boolean = false; // 🔥 3. Yükleme Kontrolü EKLENDİ

  constructor(
    private orderService: OrderService,
    private customerService: CustomerService,
    private productService: ProductService,
    private employeeService: EmployeeService,
    private cdr: ChangeDetectorRef // 🔥 4. Constructor'a EKLENDİ
  ) {}

  ngOnInit(): void {
    this.getAllCustomers();
    this.getAllProducts();
    this.getAllEmployees();
    
    // Listeyi çek
    this.getAllOrders();

    this.addNewLine();
  }

  // --- KRİTİK NOKTA: VERİ ÇEKME GÜNCELLEMESİ ---
  getAllOrders() {
    this.isLoading = true; // Yükleme başladı
    console.log("⏳ Siparişler çekiliyor...");

    this.orderService.getAllOrders()
      .pipe(
        // İşlem bitince (Hata olsa bile) burası çalışır
        finalize(() => {
          this.isLoading = false; 
          this.cdr.detectChanges(); // 🔥 5. EKRANI ZORLA GÜNCELLE
          console.log("🏁 Yükleme bitti.");
        })
      )
      .subscribe({
        next: (res: any) => {
          console.log("✅ Gelen Liste:", res);
          this.orderList = res;
        },
        error: (err) => {
          console.error("❌ Hata:", err);
        }
      });
  }

  // Diğer basit get metodları
  getAllCustomers() {
    this.customerService.getAllCustomer().subscribe((res: any) => this.customerList = res);
  }
  getAllEmployees() {
    this.employeeService.getAllEmployees().subscribe((res: any) => this.employeeList = res);
  }
  getAllProducts() {
    this.productService.getAllProduct().subscribe((res: any) => this.productList = res);
  }

  // --- SATIR YÖNETİMİ ---
  addNewLine() {
    this.order.items.push({
      productId: 0,
      productName: '',
      quantity: 1,
      unitPrice: 0,
      subTotal: 0
    });
  }

  removeLine(index: number) {
    this.order.items.splice(index, 1);
    this.calculateGrandTotal();
  }

  onProductChange(index: number) {
    const row = this.order.items[index];
    // productId string gelirse diye '==' kullanıyoruz (tip dönüşümü için)
    const selectedProduct = this.productList.find(p => p.id == row.productId);

    if (selectedProduct) {
      row.unitPrice = selectedProduct.price;
      row.productName = selectedProduct.name;
      this.calculateRowTotal(index);
    }
  }

  calculateRowTotal(index: number) {
    const row = this.order.items[index];
    row.subTotal = row.quantity * row.unitPrice;
    this.calculateGrandTotal();
  }

  calculateGrandTotal() {
    this.grandTotal = 0;
    for (let item of this.order.items) {
      this.grandTotal += item.subTotal;
    }
  }

  // --- KAYDETME ---
  save() {
    console.log("Giden Sipariş:", this.order);

    this.orderService.saveOrder(this.order).subscribe({
      next: (res) => {
        alert("✅ Sipariş Başarıyla Oluşturuldu! Fiş No: " + res.id);
        
        // Kaydettikten sonra listeyi yenile
        this.getAllOrders(); 
        
        // Formu temizle
        this.order = {
            customerId: 0,
            employeeId: 0,
            code: '',
            status: 'PENDING',
            orderDate: '', 
            totalAmount: 0,
            items: []
        };
        this.addNewLine();
        this.grandTotal = 0;
      },
      error: (err) => {
        console.error("Hata Detayı:", err);
        alert("❌ Kayıt başarısız! Konsola bak.");
      }
    });
  }
}