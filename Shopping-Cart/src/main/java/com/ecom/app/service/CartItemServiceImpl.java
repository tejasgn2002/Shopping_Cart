package com.ecom.app.service;

import com.ecom.app.entity.Cart;
import com.ecom.app.entity.CartItem;
import com.ecom.app.entity.Product;
import com.ecom.app.exceptions.CartItemNotFoundException;
import com.ecom.app.exceptions.CartNotFoundException;
import com.ecom.app.exceptions.ProductNotFoundException;
import com.ecom.app.repository.CartItemRepository;
import com.ecom.app.repository.CartRepository;
import com.ecom.app.repository.ProductRepository;
import com.ecom.app.requestbody.CartItemRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class CartItemServiceImpl implements CartItemService {

    private static final Logger logger =
            LoggerFactory.getLogger(CartItemServiceImpl.class);

    @Autowired
    private CartRepository cartRepo;

    @Autowired
    private ProductRepository productRepo;

    @Autowired
    private CartItemRepository cartItemRepo;

    @Override
    public ResponseEntity<?> addCartItem(int cartId, CartItemRequest cartItemRequest) {

        logger.info("Add cart item request received: cartId={}, productId={}, quantity={}",
                cartId, cartItemRequest.getProductId(), cartItemRequest.getQuantity());

        Cart cart = cartRepo.findById(cartId).orElseThrow(()-> {
            logger.warn("Invalid cart. cartId={}",cartId);
            return new CartNotFoundException("Cart Not Found");
        });
        Product product = productRepo.findById(cartItemRequest.getProductId()).orElseThrow(
                ()->{
                    logger.warn("Invalid Product. productId={}",cartItemRequest.getProductId());
                    return new ProductNotFoundException("Product Not Found");
                });

//        if (cart.getCartId() == null || product.getProductId() == null) {
//            logger.warn("Invalid cart or product. cartId={}, productId={}",
//                    cartId, cartItemRequest.getProductId());
//
//            return new ResponseEntity<>("Invalid Details", HttpStatus.BAD_REQUEST);
//        }

        CartItem cartItem = cartItemRepo.findByCartAndProduct(cart, product).orElseGet(() -> {
            logger.debug("CartItem not found for cartId={} and productId={}, creating new CartItem",
                    cartId, cartItemRequest.getProductId());
            CartItem cartItem1 = new CartItem();
            cartItem1.setCart(cart);
            cartItem1.setProduct(product);
            cartItem1.setQuantity(0);
            return cartItem1;
        });

        cartItem.setQuantity(cartItem.getQuantity() + cartItemRequest.getQuantity());

        logger.info("Saving cart item: cartId={}, productId={}, finalQuantity={}",
                cartId, product.getProductId(), cartItem.getQuantity());

        return new ResponseEntity<>(cartItemRepo.save(cartItem), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> updateQuantity(int cartId, int itemId, int quantity) {

        logger.info("Update quantity request: cartId={}, itemId={}, quantity={}",
                cartId, itemId, quantity);

        CartItem cartItem = cartItemRepo.findByCartIdAndCartItemId(cartId,itemId).orElseThrow(()->{
            logger.warn("Invalid update attempt for itemId={} and cartId={}",
                   itemId, cartId);
            return new CartItemNotFoundException("Cart Item Not Found");
        });

//        System.out.println(cartItem.getCart().getCartId());

//        if (cartItem.getCart().getCartId() != cartId) {
//            logger.warn("Invalid update attempt for itemId={} and cartId={}",
//                    itemId, cartId);
//            return new ResponseEntity<>("Invalid Details", HttpStatus.BAD_REQUEST);
//        }

        cartItem.setQuantity(quantity);

        logger.info("Quantity updated successfully for itemId={}, newQuantity={}",
                itemId, quantity);

        return new ResponseEntity<>(cartItemRepo.save(cartItem), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> fetchCartItemById(int cartItemId) {
        logger.info("Fetch cart item by id request: cartItemId={}", cartItemId);
        return null;
    }

    @Override
    public ResponseEntity<?> fetchAllCartItems() {
        logger.info("Fetch all cart items request received");
        return null;
    }

    @Override
    public ResponseEntity<?> deleteCartItem(int itemId) {

        logger.info("Delete cart item request received: itemId={}", itemId);

        if (cartItemRepo.findById(itemId).isEmpty()) {
            logger.warn("Cart item deletion failed, item not found: itemId={}", itemId);
            throw new CartItemNotFoundException("Cart Item Not Found");
        }

        cartItemRepo.deleteById(itemId);

        logger.info("Cart item deleted successfully: itemId={}", itemId);

        return new ResponseEntity<>("Item Deleted Successfully", HttpStatus.OK);
    }
}
