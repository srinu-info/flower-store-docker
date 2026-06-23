import { Component, inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { Router } from '@angular/router';

import { OrderService } from '../../services/order';

@Component({
  selector: 'app-checkout',
  standalone: true,
  imports: [
    CommonModule,
    FormsModule
  ],
  templateUrl: './checkout.html'
})
export class Checkout {

  private orderService = inject(OrderService);
  private router = inject(Router);

  deliveryAddress = '';

  paymentMode = 'COD';

  placeOrder() {

    const payload = {

      deliveryAddress: this.deliveryAddress,

      paymentMode: this.paymentMode

    };

    this.orderService
      .placeOrder(payload)
      .subscribe({

        next: () => {

          alert('Order Placed Successfully');

          this.router.navigate(['/orders']);

        },

        error: err => {

          console.error(err);

          alert('Failed to place order');

        }

      });

  }

}