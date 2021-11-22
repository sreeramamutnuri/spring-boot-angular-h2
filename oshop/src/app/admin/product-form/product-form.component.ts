import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { CategoryService } from 'src/app/category.service';
import { ProductService } from 'src/app/product.service';
import 'rxjs/add/operator/take'; 

@Component({
  selector: 'app-product-form',
  templateUrl: './product-form.component.html',
  styleUrls: ['./product-form.component.css']
})
export class ProductFormComponent implements OnInit {
    categories$;
    product={
      "name":"",
      "price":"",
      "imageUrl":"",
      "category_id":"",
      "category": {
        "id": ""
      }
    };
    id;

  constructor(private categoryService:  CategoryService, 
    private route: ActivatedRoute,
    private router: Router,
    private productService: ProductService) { 
    this.categories$= this.categoryService.getCategories();

    this.id= this.route.snapshot.paramMap.get("id");
    if(this.id) productService.get(this.id)
                              .take(1)
                              .subscribe( product=> this.product=product);
  }

  save(product){

    if(this.id){
      product.id=this.id;
      this.productService.update(product);
    } else{
      this.productService.create(product);
    } 
        
    this.router.navigate(['admin/products']);
  }

  delete(){
    this.productService.delete(this.id);
    this.router.navigate(['admin/products']);
  }

  ngOnInit(): void {

  }

}
