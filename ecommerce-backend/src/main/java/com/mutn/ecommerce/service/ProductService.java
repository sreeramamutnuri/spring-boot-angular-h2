package com.mutn.ecommerce.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mutn.ecommerce.entity.Category;
import com.mutn.ecommerce.entity.Product;
import com.mutn.ecommerce.repository.CategoriesRepository;
import com.mutn.ecommerce.repository.ProductRepository;

@Service
public class ProductService {
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private CategoriesRepository categoryRepository;
	
	public List<Product> getAllProducts(){
		//return productRepository.findAll();
		return productRepository.findProducts();
	}
	
	public Optional<Product> getAllProductById(Long productId){
		return productRepository.findById(productId);
	}
	
	public List<Product> getProductByCategoryId(Long categoryId){
		List<Product> list=productRepository.findProducts();
		return list.stream().filter(p -> p.getCategory().getId() == categoryId).collect(Collectors.toList());
	}
	
		
	public List<Category> getAllCategories(){
		return categoryRepository.findAll();
	}
	
	public Optional<Product> getProductById(Long productId){
		System.out.println("id "+ productId);
		return productRepository.findById(productId);
	}
	
	@Transactional
	public Product save(Product product) {
		
		Optional<Category> category= categoryRepository.findById(product.getCategory().getId());
		
		if(category.isPresent()) {
		  categoryRepository.save(category.get());
		}else {
			categoryRepository.save(product.getCategory());
		}
		
		return productRepository.save(product);
	}
	
	@Transactional
	public void delete(Product product) {
		productRepository.delete(product);
	}

}
