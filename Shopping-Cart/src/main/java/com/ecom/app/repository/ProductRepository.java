package com.ecom.app.repository;

import com.ecom.app.entity.Category;
import com.ecom.app.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product,Integer> {
    List<Product> findAllByCategory(Category category);
}
