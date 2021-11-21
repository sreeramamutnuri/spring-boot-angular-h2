package com.mutn.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.mutn.ecommerce.entity.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long>{
	
	@Query(value = "SELECT * FROM Orders WHERE user_id = :userId or session_id = :sessionId", nativeQuery = true)
	Order getOrderByUserOrSession(@Param("userId") Integer userId,@Param("sessionId") String sessionId);

}
