package com.mutn.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.mutn.ecommerce.entity.OrderItem;

@Repository
public interface OrderItemsRepository extends JpaRepository<OrderItem, Long>{

	@Query(value="select sum(price) from orderitems where order_id = :order_id", nativeQuery = true)
	Integer getOrderTotalPrice(@Param("order_id") int order_id);

}
