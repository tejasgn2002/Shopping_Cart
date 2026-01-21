package com.ecom.app.repository;

import com.ecom.app.entity.Cart;
import com.ecom.app.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository extends JpaRepository<Cart,Integer> {
    Cart findByUser(User user);
}
