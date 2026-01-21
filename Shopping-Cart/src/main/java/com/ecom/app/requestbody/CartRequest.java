package com.ecom.app.requestbody;

import com.ecom.app.entity.CartItem;
import com.ecom.app.entity.User;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartRequest {
    private Integer cartId;
    private User user;
    private List<CartItem> cartItemList;
}
