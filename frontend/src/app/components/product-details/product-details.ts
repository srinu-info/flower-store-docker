import { Component, OnInit, inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ActivatedRoute } from '@angular/router';

import { ProductService } from '../../services/product';
import { CartService } from '../../services/cart';

@Component({
  selector: 'app-product-details',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './product-details.html'
})
export class ProductDetailsComponent implements OnInit {

  private route = inject(ActivatedRoute);

  private productService = inject(ProductService);

  private cartService = inject(CartService);

  product: any;

  selectedSize = 'MEDIUM';

  ngOnInit(): void {

    const id = Number(
      this.route.snapshot.paramMap.get('id')
    );

    this.productService
      .getProductById(id)
      .subscribe(data => {

        this.product = data;
      });
  }

  selectSize(size: string) {

    this.selectedSize = size;
  }

  addToCart() {

    const request = {

      email: 'srinu@gmail.com', // temporary

      productId: this.product.id,

      quantity: 1
    };

    this.cartService
      .addToCart(request)
      .subscribe({

        next: () => {

          alert('Added To Cart');
        },

        error: () => {

          alert('Failed To Add');
        }

      });
  }

}