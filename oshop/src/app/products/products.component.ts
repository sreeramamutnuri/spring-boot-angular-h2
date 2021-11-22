import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { ProductFormComponent } from '../admin/product-form/product-form.component';
import { CategoryService } from '../category.service';
import { Product } from '../models/product';
import { ProductService } from '../product.service';

@Component({
  selector: 'app-products',
  templateUrl: './products.component.html',
  styleUrls: ['./products.component.css']
})
export class ProductsComponent {

  products$;
  categories$;
  category;

  constructor(private productService: ProductService, 
              private categoryService: CategoryService, 
              private route : ActivatedRoute) { 
    //this.products$= this.productService.getProducts();
    this.categories$= this.categoryService.getCategories();

    route.queryParamMap.subscribe( params => {
      this.category= params.get("category");
      this.products$= this.category ? this.productService.getProductsByCategory(this.category) : this.productService.getProducts();
    })

  } 

  addToCart(product: Product){
    console.log("click on product")
  }


}
