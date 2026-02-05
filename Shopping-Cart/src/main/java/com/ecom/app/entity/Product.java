package com.ecom.app.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer productId;
    private String productName;
    private double productPrice;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id",nullable = false)
    @JsonBackReference
    private Category category;
    @OneToMany(mappedBy = "product",cascade = CascadeType.REMOVE, orphanRemoval = true)
    @JsonBackReference
    private List<CartItem> cartItemList;
    private int stockQty;
}
