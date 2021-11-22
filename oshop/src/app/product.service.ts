import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import {HttpHeaders } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class ProductService {
  private url="/v1/products";
  private productCategoryUrl ="/v1/products/category";

  constructor(private httpClient: HttpClient) { }

  create(product){

    let data = {
        "name"  : product.name,
        "price" : product.price,
        "imageUrl" : product.imageUrl,
        "category" : {
          "id" : product.category_id
        } 
    }
    console.log(data);

    const headers = new HttpHeaders().set('Content-Type', 'application/json; charset=utf-8');

    this.httpClient.post(this.url,JSON.stringify(data),{headers: headers})
    .subscribe(
        response => console.log(response)
    );

  }


  update(product){

  console.log("update method");
  console.log(product);

    let data = {
        "id" : product.id,
        "name"  : product.name,
        "price" : product.price,
        "imageUrl" : product.imageUrl,
        "category" : {
          "id" : product.category_id
        } 
    }
    console.log("data for the api call", data);

    const headers = new HttpHeaders().set('Content-Type', 'application/json; charset=utf-8');

    this.httpClient.post(this.url,JSON.stringify(data),{headers: headers})
    .subscribe(
        response => console.log(response)
    );

  }

  getProducts(){
    return this.httpClient.get(this.url);
  }

  getProductsByCategory(categoryId){
    return this.httpClient.get(this.productCategoryUrl +"/"+ categoryId );
  }

  get(productId){
    return this.httpClient.get(this.url+"/"+productId);
  }

  delete(productId){
    this.httpClient.delete(this.url+"/"+productId).subscribe(
      response => console.log(response)
    );
  }

}
