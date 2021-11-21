package com.mutn.ecommerce.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mutn.ecommerce.entity.Order;
import com.mutn.ecommerce.entity.OrderItem;
import com.mutn.ecommerce.repository.OrderItemsRepository;
import com.mutn.ecommerce.repository.OrderRepository;

@Service
public class OrdersService {

  @Autowired	
  private OrderRepository orderRepository;
  
  @Autowired
  private OrderItemsRepository orderItemsRepository;

  @Transactional
  public Order save(Order order) {
	  return orderRepository.save(order);
  }
  
  @Transactional
  public void saveOrderItems(OrderItem orderItem) {
	  orderItemsRepository.save(orderItem);
  }
  
  public List<Order> getAllOrders() {
	  return orderRepository.findAll();
  }
  
  public Order getOrderByUserOrSession(Integer userId,String sessionId) {
	  return orderRepository.getOrderByUserOrSession(userId, sessionId);
  }
 
  public Integer getOrderTotalPrice(int order_id) {
	  return orderItemsRepository.getOrderTotalPrice(order_id);
  }
  
}
