import {
  ChangeDetectorRef,
  Component,
  OnInit,
  inject
} from '@angular/core';

import {
  CommonModule
} from '@angular/common';

import {
  RouterModule
} from '@angular/router';

import { CartService }
from '../../services/cart';

@Component({
  selector: 'app-cart',
  standalone: true,
  imports: [
    CommonModule,
    RouterModule
  ],
  templateUrl: './cart.html'
})
export class Cart implements OnInit {

  private cartService =
    inject(CartService);

  cartItems:any[]=[];
private cdr = inject(ChangeDetectorRef);
  grandTotal=0;

  successMessage='';

  ngOnInit(): void {

    this.loadCart();

  }

  loadCart() {

  this.cartService
    .getCart()
    .subscribe({

      next: (response:any) => {

        console.log('Cart Response', response);

        this.cartItems =
          response.items || [];

        this.calculateTotal();

        this.cdr.detectChanges();

      },

      error: (err) => {

        console.error(err);

      }

    });

}

  calculateTotal(){

    this.grandTotal = 0;

    this.cartItems.forEach(item => {

      this.grandTotal +=
        item.subtotal;

    });

  }

  increaseQty(item:any){

    this.cartService
      .updateQuantity(
        item.id,
        item.quantity + 1
      )
      .subscribe(() => {

        this.loadCart();

      });

  }

  decreaseQty(item:any){

    if(item.quantity <= 1){
      return;
    }

    this.cartService
      .updateQuantity(
        item.id,
        item.quantity - 1
      )
      .subscribe(() => {

        this.loadCart();

      });

  }

  deleteItem(id:number){

    this.cartService
      .removeItem(id)
      .subscribe(() => {

        this.successMessage =
          'Item Removed Successfully';

        this.loadCart();

        setTimeout(() => {

          this.successMessage='';

        },3000);

      });

  }

}