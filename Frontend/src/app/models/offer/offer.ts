import { Component, OnInit } from '@angular/core';
import { OfferService } from '../../services/offer-service';
import { CustomerService } from '../../services/customer-service';
import { ProductService } from '../../services/product-service';
import { EmployeeService } from '../../services/employee-service';
import { ContractService } from '../../services/contract-service';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-offer',
  imports: [CommonModule,FormsModule], 
  templateUrl: './offer.html',
  styleUrls: ['./offer.css']
})
export class Offer implements OnInit {

  offer: any = {
    customerId: 0,
    employeeId: 0,
    code: '',
    date: '',
    description: '',
    status: 'DRAFT',
    items: []
  };

  // Listeler
  customerList: any[] = [];
  productList: any[] = [];
  employeeList: any[] = [];
  offerList: any[] = [];

  grandTotal: number = 0;
  
  isListVisible: boolean = false; 

  isLoading: boolean = false;

  constructor(
    private offerService: OfferService,
    private customerService: CustomerService,
    private productService: ProductService,
    private employeeService: EmployeeService,
    private contractService: ContractService
  ) {}

  ngOnInit(): void {
    // Sadece Dropdownları dolduruyoruz (Müşteri, Ürün, Personel)
    this.getAllCustomers();
    this.getAllProducts();
    this.getAllEmployees();

    // ❌ ARTIK BURADA getAllOffers() ÇAĞIRMIYORUZ!
    // Sayfa açılınca otomatik listeleme yapmıyoruz.
    this.addNewLine();
  }

  getAllCustomers() {
    this.customerService.getAllCustomer().subscribe((res: any) => this.customerList = res);
  }
  
  getAllEmployees() {
    this.employeeService.getAllEmployees().subscribe((res: any) => this.employeeList = res);
  }

  getAllProducts() {
    this.productService.getAllProduct().subscribe((res: any) => this.productList = res);
  }

  getAllOffers() {
    console.log("🖱️ Butona basıldı...");
    
    // 1. HAMLE: Butona basar basmaz tabloyu görünür yap! (Beklemek yok)
    this.isListVisible = true; 

    // 2. HAMLE: Sonra veriyi çekmeye başla
    this.offerService.getAllOffers().subscribe({
      next: (res) => {
        console.log("✅ Veriler Geldi:", res);
        this.offerList = res;
      },
      error: (err) => {
        console.error("❌ Hata:", err);
        // Hata olursa tabloyu geri gizle ki kullanıcı tekrar deneyebilsin
        this.isListVisible = false; 
        alert("Veriler çekilemedi!");
      }
    });
  }

  addNewLine() {
    this.offer.items.push({ productId: 0, productName: '', quantity: 1, price: 0, totalAmount: 0 });
  }

  removeLine(index: number) {
    this.offer.items.splice(index, 1);
    this.calculateGrandTotal(); 
  }

  onProductChange(index: number) {
    const row = this.offer.items[index];
    const selectedProduct = this.productList.find(p => p.id == row.productId);
    if (selectedProduct) {
      row.price = selectedProduct.price; 
      row.productName = selectedProduct.name; 
      this.calculateRowTotal(index); 
    }
  }

  calculateRowTotal(index: number) {
    const row = this.offer.items[index];
    row.totalAmount = row.quantity * row.price;
    this.calculateGrandTotal(); 
  }

  calculateGrandTotal() {
    this.grandTotal = 0;
    for (let item of this.offer.items) {
      this.grandTotal += item.totalAmount;
    }
  }

  save() {
    this.offerService.saveOffer(this.offer).subscribe({
      next: (res) => {
        alert("✅ Kayıt Başarılı! Fiş No: " + res.code);
        
        // Kaydettikten sonra listeyi otomatik yenilemek İSTEMİYORSAN burayı da sil.
        // Ama genelde kayıttan sonra görmek istenir, o yüzden bıraktım.
        this.getAllOffers(); 

        this.offer = { customerId: 0, employeeId: 0, code: '', date: '', description: '', status: 'DRAFT', items: [] };
        this.addNewLine();
        this.grandTotal = 0;
      },
      error: (err) => alert("Hata oluştu!")
    });
  }

  createContract(offerId: number) {
    if(!confirm("Sözleşmeye dönüştürülsün mü?")) return;
    this.contractService.convertOfferToContract(offerId).subscribe({
      next: (res) => {
        alert("Sözleşme Oluştu: " + res.code);
        this.getAllOffers();
      }
    });
  }
}