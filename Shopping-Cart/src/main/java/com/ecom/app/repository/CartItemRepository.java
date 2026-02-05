package com.ecom.app.repository;

import com.ecom.app.entity.Cart;
import com.ecom.app.entity.CartItem;
import com.ecom.app.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem,Integer> {
    Optional<CartItem> findByCartAndProduct(Cart cart, Product product);
    Optional<CartItem> findByCartAndCartItemId(Cart cart, int CartItemId);
}
