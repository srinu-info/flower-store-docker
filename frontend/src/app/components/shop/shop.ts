import { ChangeDetectorRef, Component, OnInit, inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

import { ProductService } from '../../services/product';
import { CartService } from '../../services/cart';

@Component({
  selector: 'app-shop',
  standalone: true,
  imports: [
    CommonModule,
    FormsModule
  ],
  templateUrl: './shop.html'
})
export class Shop implements OnInit {

  private productService = inject(ProductService);

  private cartService = inject(CartService);
  private cdr =
  inject(ChangeDetectorRef);

  products: any[] = [];

  filteredProducts: any[] = [];

  selectedCategory = '';

  searchText = '';
  successMessage = '';

  sortBy = '';

  expandedProductId: number | null = null;

  selectedSize = 'MEDIUM';
  selectedPrice=0;

  quantity = 1;

  ngOnInit(): void {

    this.loadProducts();

  }

 loadProducts() {

  this.productService
    .getAllProducts()
    .subscribe({

      next: (data:any) => {

        this.products = data || [];

        this.filteredProducts =
          [...this.products];

        this.cdr.detectChanges();

      },

      error: (err) => {

        console.error(err);

      }

    });

}
  filterProducts() {

    this.filteredProducts = this.products.filter(product => {

      const categoryMatch =
        !this.selectedCategory ||
        product.categories?.includes(this.selectedCategory);

      const searchMatch =
        !this.searchText ||
        product.name
          .toLowerCase()
          .includes(this.searchText.toLowerCase());

      return categoryMatch && searchMatch;

    });

    this.applySorting();

  }

  applySorting() {

    if (this.sortBy === 'name') {

      this.filteredProducts.sort((a, b) =>
        a.name.localeCompare(b.name)
      );

    }

    if (this.sortBy === 'priceLow') {

      this.filteredProducts.sort((a, b) =>
        (a.price || 0) - (b.price || 0)
      );

    }

    if (this.sortBy === 'priceHigh') {

      this.filteredProducts.sort((a, b) =>
        (b.price || 0) - (a.price || 0)
      );

    }

  }

  toggleProduct(productId:number){

  if(this.expandedProductId===productId){

    this.expandedProductId=null;

  }
  else{

    this.expandedProductId=productId;

    this.selectedSize='';

    this.selectedPrice=0;

  }

}
  getSelectedPrice(product:any){

  const sizeObj =
    product.sizes?.find(
      (s:any) =>
        s.size === this.selectedSize
    );

  this.selectedPrice =
    sizeObj?.price || 0;

}

 addToCart(product: any) {

  const payload = {

    productId: product.id,

    size: this.selectedSize,

    quantity: this.quantity

  };

  this.cartService
    .addToCart(payload)
    .subscribe({

      next: (response) => {

  console.log('ADD TO CART SUCCESS');

  alert('Product Added To Cart');

  this.successMessage =
    '✅ Product added to cart successfully';

},

      error: (err) => {

        console.error(err);

        this.successMessage =
          '❌ Failed to add product to cart';

        setTimeout(() => {

          this.successMessage = '';

        }, 3000);

      }

    });

}

}