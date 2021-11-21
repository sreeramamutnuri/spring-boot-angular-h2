package com.mutn.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mutn.ecommerce.entity.Category;

@Repository
public interface CategoriesRepository extends JpaRepository<Category, Long>{

}
