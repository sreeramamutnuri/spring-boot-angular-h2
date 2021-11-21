package com.mutn.ecommerce.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.mutn.ecommerce.entity.Category;
import com.mutn.ecommerce.entity.Product;
import com.mutn.ecommerce.service.ProductService;

@RestController
public class ProductController {
	
	@Autowired
	private ProductService productService;
	
	@GetMapping("/v1/products")
	public ResponseEntity<List<Product>> getAllProducts(){
		return new ResponseEntity<List<Product>>(productService.getAllProducts(), HttpStatus.OK);
	}
	
	@GetMapping("/v1/categories")
	public ResponseEntity<List<Category>> getAllCategories(){
		return new ResponseEntity<>(productService.getAllCategories(), HttpStatus.OK);
	}
	
	@PostMapping("/v1/products")
	public ResponseEntity<Product> createProduct(@RequestBody Product product){
		return new ResponseEntity<>(productService.save(product), HttpStatus.OK);
	}
	
	@GetMapping("/v1/products/{id}")
	public ResponseEntity<Product> getProducts(@PathVariable("id") Long productId){
		return new ResponseEntity<>(productService.getAllProductById(productId).get(), HttpStatus.OK);
	}
	
	@GetMapping("/v1/products/category/{id}")
	public ResponseEntity<List<Product>> getCategoryProducts(@PathVariable("id") Long categoryId){
		return new ResponseEntity<>(productService.getProductByCategoryId(categoryId), HttpStatus.OK);
	}
	
	@DeleteMapping("/v1/products/{id}")
	public ResponseEntity deleteProduct(@PathVariable("id") Long productId){
		System.out.println("delete method"+ productId);
		Product product = new Product();
		product.setId(productId);
		productService.delete(product);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
