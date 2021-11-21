package com.mutn.ecommerce.controller;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.mutn.ecommerce.entity.CartItem;
import com.mutn.ecommerce.entity.Order;
import com.mutn.ecommerce.entity.OrderItem;
import com.mutn.ecommerce.entity.Product;
import com.mutn.ecommerce.service.OrdersService;
import com.mutn.ecommerce.service.ProductService;

@RestController
public class OrdersController {
	
	@Autowired
	private OrdersService ordersService;
	
	@Autowired
	private ProductService productService;
	
	@PostMapping("/v1/orders")
	public ResponseEntity<Order> addToCart(@RequestBody CartItem cartItem) {
		System.out.println("productId :" + cartItem.getProductId());
		System.out.println("qty :" + cartItem.getQty());
		
		Integer userId= cartItem.getUser()!=null ? cartItem.getUser().getId() :0; 
		String sessionId= cartItem.getSessionId()!=null ? cartItem.getSessionId() :"";

		Optional<Product> product= productService.getProductById(cartItem.getProductId());
		
		Order order= ordersService.getOrderByUserOrSession(userId, sessionId);
		
		if(order==null) {
			order= new Order();
			order.setSessionId(UUID.randomUUID().toString());
			order.setCreatedDate(new Date());
			order= ordersService.save(order);
		}
		
		OrderItem item= new OrderItem();
		item.setProduct(product.get());
		item.setOrder(order);
		item.setQuantity(cartItem.getQty());
		item.setPrice(cartItem.getQty()*product.get().getPrice());
		item.setCreatedDate(new Date());
		
		ordersService.saveOrderItems(item);
		
		int totalPrice= ordersService.getOrderTotalPrice(order.getId());
		System.out.println(totalPrice);
		
		order.setTotalPrice(totalPrice);
		order = ordersService.save(order);
		
		System.out.println("order :"+ order);
		
		return new ResponseEntity<Order>(order,HttpStatus.OK);
		
	}
	
	@GetMapping("/v1/orders")
	public ResponseEntity<List<Order>> getOrders(){
		List<Order> ordersList= ordersService.getAllOrders();
		return new ResponseEntity<List<Order>>(ordersList, HttpStatus.OK);
	}
	 
}
