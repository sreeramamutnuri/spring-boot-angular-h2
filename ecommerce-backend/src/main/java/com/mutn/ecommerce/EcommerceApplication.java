package com.mutn.ecommerce;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.mutn.ecommerce.entity.Category;
import com.mutn.ecommerce.entity.Product;
import com.mutn.ecommerce.repository.CategoriesRepository;
import com.mutn.ecommerce.repository.ProductRepository;

@SpringBootApplication
public class EcommerceApplication implements CommandLineRunner{
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private CategoriesRepository categoryRepository;


	public static void main(String[] args) {
		SpringApplication.run(EcommerceApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		 
		Category c1= categoryRepository.save(new Category("Electronics","",""));
		
		Product objProduct = new Product("Monitor",100.0,"https://i.ebayimg.com/00/s/MTE1NlgxNTAw/z/8T8AAOSwTR9d1DrW/$_57.JPG","",c1);
		productRepository.save(objProduct);
		
		objProduct = new Product("Mouse",10.0,"https://media.kohlsimg.com/is/image/kohls/2025049?wid=1200&hei=1200&op_sharpen=1","",c1);
		productRepository.save(objProduct);
		
		objProduct = new Product("Laptop",1800.0,"https://store.storeimages.cdn-apple.com/4982/as-images.apple.com/is/macbook-air-silver-config-201810?wid=1078&hei=624&fmt=jpeg&qlt=80&.v=1603332212000","",c1);
		productRepository.save(objProduct);
		
		objProduct = new Product("TV",1900.0,"https://image-us.samsung.com/SamsungUS/home/televisions-and-home-theater/tvs/qled-4k-tvs/q80a/gallery/02232021/QN65Q80AAFXZA_001_Front1_Titan-Black-1600x1200.jpg?$product-details-jpg$","",c1);
		productRepository.save(objProduct);
		
		Category c2= categoryRepository.save(new Category("Fruits","",""));

		objProduct = new Product("Apple",10.0,"https://cdn.shopify.com/s/files/1/0294/1134/4521/products/apple-powder-organic-fruit-powders-z-natural-foods-258095_900x.jpg?v=1600881572","",c2);
		productRepository.save(objProduct);

	}

} 
