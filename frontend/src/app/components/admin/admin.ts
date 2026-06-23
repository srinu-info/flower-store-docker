import {
  Component,
  OnInit
  
} from '@angular/core';
import { ChangeDetectorRef, inject } from '@angular/core';
import {
  CommonModule
} from '@angular/common';

import {
  FormsModule
} from '@angular/forms';

import {
  AdminProductService
} from '../../services/admin-product';

@Component({
  selector: 'app-admin',
  standalone: true,
  imports: [
    CommonModule,
    FormsModule
  ],
  templateUrl: './admin.html'
})
export class Admin implements OnInit {

  private productService =
    inject(AdminProductService);
private cdr = inject(ChangeDetectorRef);
  products:any[] = [];

  editMode = false;

  selectedId:number | null = null;

  selectedCategory = 'BIRTHDAY';

  product:any = {

    name:'',

    description:'',

    imageUrl:'',

    bestSelling:false,

    newArrival:false,

    categories:['BIRTHDAY'],

    sizes:[
      {
        size:'SMALL',
        price:0
      },
      {
        size:'MEDIUM',
        price:0
      },
      {
        size:'LARGE',
        price:0
      }
    ]
  };

  ngOnInit(): void {

    this.loadProducts();

  }

 loadProducts() {

  this.productService.getProducts().subscribe(data => {

    this.products = [...data];

    this.cdr.detectChanges();

  });

}

 saveProduct() {

  this.product.categories = [this.selectedCategory];

  if (this.editMode) {

    this.productService.updateProduct(this.selectedId!, this.product)
      .subscribe({
        next: () => {

          alert('Product Updated');

          this.resetForm();

          this.loadProducts();

          this.cdr.detectChanges();

        },
        error: err => console.error(err)
      });

  } else {

    this.productService.addProduct(this.product)
      .subscribe({
        next: () => {

          alert('Product Added');

          this.resetForm();

          this.loadProducts();

          this.cdr.detectChanges();

        },
        error: err => console.error(err)
      });

  }

}
  editProduct(product:any){

    this.editMode = true;

    this.selectedId = product.id;

    this.selectedCategory =
      product.categories?.[0] ||
      'BIRTHDAY';

    this.product = {

      name: product.name,

      description: product.description,

      imageUrl: product.imageUrl,

      bestSelling: product.bestSelling,

      newArrival: product.newArrival,

      categories:
        product.categories || [],

      sizes:
        JSON.parse(
          JSON.stringify(
            product.sizes
          )
        )

    };

  }

  deleteProduct(id:number){

    if(!confirm(
      'Delete Product ?'
    )){
      return;
    }

    this.productService
      .deleteProduct(id)
      .subscribe(()=>{

        alert(
          'Product Deleted'
        );

        this.loadProducts();

      });

  }

  resetForm(){

    this.editMode = false;

    this.selectedId = null;

    this.selectedCategory =
      'BIRTHDAY';

    this.product = {

      name:'',

      description:'',

      imageUrl:'',

      bestSelling:false,

      newArrival:false,

      categories:['BIRTHDAY'],

      sizes:[
        {
          size:'SMALL',
          price:0
        },
        {
          size:'MEDIUM',
          price:0
        },
        {
          size:'LARGE',
          price:0
        }
      ]

    };

  }

}