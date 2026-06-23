import {
  Component,
  OnInit,
  inject,
  ChangeDetectorRef
} from '@angular/core';

import { CommonModule } from '@angular/common';

import { OrderService } from '../../services/order';

@Component({
  selector: 'app-orders',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './orders.html'
})
export class Orders implements OnInit {

  private orderService = inject(OrderService);

  private cdr =
    inject(ChangeDetectorRef);

  orders:any[]=[];

  ngOnInit(): void {

    this.loadOrders();

  }

  loadOrders(){

    this.orderService
      .getOrders()
      .subscribe({

        next:(response:any)=>{

          console.log(
            'ORDERS API',
            response
          );

          this.orders =
            [...response];

          this.cdr.detectChanges();

        },

        error:(err)=>{

          console.error(err);

        }

      });

  }

}