package com.ecom.app.requestbody;

import com.ecom.app.entity.Category;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductRequest {
    private String productName;
    private double productPrice;
    private int categoryId;
    private int stockQty;
}
