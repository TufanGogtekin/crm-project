import { ChangeDetectorRef, Component } from '@angular/core';
import { ProductInterface } from '../../interfaces/product-interface';
import { ProductService } from '../../services/product-service';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-product',
  imports : [CommonModule,FormsModule],
  templateUrl: './product.html',
  styleUrl: './product.css',
})
export class Product implements ProductInterface {
  name: string = "";
  price: number = 0;
  description: string = "";

  constructor(
    private proServe: ProductService,
    private cdr:ChangeDetectorRef) { }

  public productList: ProductInterface[] = [];

  save() {

    const tempProduct: ProductInterface = {
      name: this.name,
      price: this.price,
      description: this.description
    }

    this.proServe.saveProduct(tempProduct).subscribe({
      next: (Response) => {
        console.log("işlem tamam", Response);
        alert("kayıt oluştu");
        this.list();
        this.name = ""; 
        this.price = 0;
        this.description = "";
        this.cdr.detectChanges();
      },
      error: (err) => {
        console.log("işlem tamam değil", err);
        alert("kayıtlanma gerçekleşmedi");
      }
    })

  }

  list() {

    this.proServe.getAllProduct().subscribe({
      next: (res: any) => {
        console.log("işlem başarılı");
        this.productList = res;
        this.cdr.detectChanges();
      },
      error: (err: any) => {
        console.log("işlem başarısız",err);
      }
    })
  }
}
