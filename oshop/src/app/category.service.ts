import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class CategoryService {
  private url="/v1/categories";

  constructor(private httpClient: HttpClient) { }

  getCategories(){
    return this.httpClient.get(this.url);
  }
}
