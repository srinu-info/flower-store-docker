export interface ProductSize {
  id:number;
  size:string;
  price:number;
}

export interface Product {

  id:number;

  name:string;

  description:string;

  imageUrl:string;

  bestSelling:boolean;

  newArrival:boolean;

  categories:string[];

  sizes:ProductSize[];
}