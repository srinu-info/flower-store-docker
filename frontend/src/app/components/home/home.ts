import {
  Component,
  OnInit,
  inject,
  ChangeDetectorRef
} from '@angular/core';

import {
  CommonModule
} from '@angular/common';

import {
  RouterModule
} from '@angular/router';

import {
  ProductService
} from '../../services/product';

@Component({
  selector: 'app-home',
  standalone: true,
  imports: [
    CommonModule,
    RouterModule
  ],
  templateUrl: './home.html'
})
export class Home implements OnInit {

  private productService =
    inject(ProductService);

  private cdr =
    inject(ChangeDetectorRef);

  products:any[] = [];

  bestSellingProducts:any[] = [];

  newArrivalProducts:any[] = [];

  ngOnInit(): void {

    this.loadProducts();

  }

  loadProducts() {

    this.productService
      .getAllProducts()
      .subscribe({

        next: (data:any) => {

          this.products = data || [];

          this.bestSellingProducts =
            this.products.filter(
              p => p.bestSelling
            );

          this.newArrivalProducts =
            this.products.filter(
              p => p.newArrival
            );

          this.cdr.detectChanges();

        },

        error: (err) => {

          console.error(err);

        }

      });

  }

}