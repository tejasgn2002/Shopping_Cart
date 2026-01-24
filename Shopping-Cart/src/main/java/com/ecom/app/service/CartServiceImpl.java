package com.ecom.app.service;

import com.ecom.app.entity.Cart;
import com.ecom.app.entity.CartItem;
import com.ecom.app.entity.User;
import com.ecom.app.exceptions.CartAlreadyExistsException;
import com.ecom.app.exceptions.CartNotFoundException;
import com.ecom.app.exceptions.UserNotFoundException;
import com.ecom.app.repository.CartRepository;
import com.ecom.app.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CartServiceImpl implements CartService {

    private static final Logger logger = LoggerFactory.getLogger(CartServiceImpl.class);

    @Autowired
    private CartRepository cartRepo;

    @Autowired
    private UserRepository userRepo;

    @Override
    public ResponseEntity<?> addCart(int userID) {
        logger.info("Request received to create cart for userId={}", userID);

        User user = userRepo.findById(userID).orElseThrow(
                ()->{
                    logger.warn("User not found with userId={}", userID);
                    return new UserNotFoundException("User Not Found");
                }
        );
//        if (user == null) {
//            logger.warn("User not found with userId={}", userID);
//            return new ResponseEntity<>("User not found", HttpStatus.BAD_REQUEST);
//        }

        Cart existingCart = cartRepo.findByUser(user);
        if (existingCart != null) {
            logger.info("Cart already exists for userId={}", userID);
            throw new CartAlreadyExistsException("Cart already exists");
        }

        Cart cart = new Cart();
        cart.setUser(user);

        Cart savedCart = cartRepo.save(cart);
        logger.info("Cart created successfully with cartId={} for userId={}",
                savedCart.getCartId(), userID);

        return new ResponseEntity<>(savedCart, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> fetchTotalOfCartItems(int cartId) {
        logger.info("Fetching total price for cartId={}", cartId);

        Cart cart = cartRepo.findById(cartId).orElseThrow(()->{
            return new CartNotFoundException("Cart Not Found");
        });
//        if (cart == null) {
//            logger.warn("Cart not found with cartId={}", cartId);
//            return new ResponseEntity<>("Invalid Details", HttpStatus.OK);
//        }

        double total = 0;
        for (CartItem cartItem : cart.getCartItemList()) {
            double itemTotal =
                    cartItem.getProduct().getProductPrice() * cartItem.getQuantity();
            total += itemTotal;

            logger.debug("CartId={}, ProductId={}, Quantity={}, ItemTotal={}",
                    cartId,
                    cartItem.getProduct().getProductId(),
                    cartItem.getQuantity(),
                    itemTotal);
        }

        logger.info("Total price for cartId={} is {}", cartId, total);
        return new ResponseEntity<>("Total Price is " + total, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> fetchCartById(int cartId) {
        logger.info("Fetching cart details for cartId={}", cartId);
        Cart cart = cartRepo.findById(cartId).orElseThrow(
                ()->{
                    return  new CartNotFoundException("Cart Not Found");
                }
        );
        return new ResponseEntity<>(cart, HttpStatus.OK);
    }


@Override
    public ResponseEntity<?> fetchAllCarts() {
        return null;
    }

    @Override
    public ResponseEntity<?> deleteCart() {
        return null;
    }
}
