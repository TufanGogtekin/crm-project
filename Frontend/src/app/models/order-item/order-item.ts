import { Component } from '@angular/core';
import { OrderItemInterface } from '../../interfaces/order-item-interface';
import { OrderItemService } from '../../services/order-item-service';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-order-item',
  imports : [CommonModule,FormsModule],
  templateUrl: './order-item.html',
  styleUrl: './order-item.css',
})
export class OrderItem implements OrderItemInterface {
  quantity: number = 0;
  orderId: number = 0;
  productId: number = 0;

  constructor(private orderItemSer:OrderItemService){}

  save(){
    const tempOrderItem:OrderItemInterface = {
      quantity:this.quantity,
      orderId:this.orderId,
      productId:this.productId
    }

    this.orderItemSer.saveOrderItem(tempOrderItem).subscribe({
      next: (Response) => {
        console.log("işlem tamam");
        alert("işlem başarılı");
      },
      error: (err) => {
        console.log("işlem başarısız");
        alert("işlem başarısız");
      }
    })
  }
  
 
}
