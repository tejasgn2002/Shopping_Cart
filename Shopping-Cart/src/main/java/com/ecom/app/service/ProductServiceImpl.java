package com.ecom.app.service;

import com.ecom.app.entity.Category;
import com.ecom.app.entity.Product;
import com.ecom.app.exceptions.CategoryNotFoundException;
import com.ecom.app.exceptions.ProductNotFoundException;
import com.ecom.app.repository.CategoryRepository;
import com.ecom.app.repository.ProductRepository;
import com.ecom.app.requestbody.ProductRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    private static final Logger logger =
            LoggerFactory.getLogger(ProductServiceImpl.class);

    @Autowired
    private ProductRepository productRepo;

    @Autowired
    private CategoryRepository categoryRepo;

    @Override
    public ResponseEntity<?> addProduct(ProductRequest productRequest) {

        logger.info("Add product request received: productName={}, categoryId={}, price={}, stockQty={}",
                productRequest.getProductName(),
                productRequest.getCategoryId(),
                productRequest.getProductPrice(),
                productRequest.getStockQty());

        Category category = categoryRepo.findById(productRequest.getCategoryId())
                .orElseThrow(()->{
                    return new CategoryNotFoundException("Category Not Found");
                });

//        if (category.getCategoryId() == null) {
//            logger.warn("Invalid category while adding product. categoryId={}",
//                    productRequest.getCategoryId());
//            return new ResponseEntity<>("Invalid Category Details", HttpStatus.OK);
//        }

        Product product = new Product();
        product.setProductName(productRequest.getProductName());
        product.setProductPrice(productRequest.getProductPrice());
        product.setCategory(category);
        product.setStockQty(productRequest.getStockQty());

        logger.info("Saving product: productName={}, categoryId={}",
                product.getProductName(),
                category.getCategoryId());

        return new ResponseEntity<>(productRepo.save(product), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> addProducts(List<ProductRequest> productRequestList) {

        logger.info("Add multiple products request received. Count={}",
                productRequestList.size());

        List<Product> productList = new ArrayList<>();

        productRequestList.forEach(productRequest -> {

            logger.debug("Processing product: productName={}, categoryId={}",
                    productRequest.getProductName(),
                    productRequest.getCategoryId());

            Category category = categoryRepo.findById(productRequest.getCategoryId())
                    .orElseThrow(
                            ()->{
                                return new CategoryNotFoundException("Category Not Found");
                            }
                    );

            if (category.getCategoryId() != null) {

                Product product = new Product();
                product.setProductName(productRequest.getProductName());
                product.setProductPrice(productRequest.getProductPrice());
                product.setCategory(category);
                product.setStockQty(productRequest.getStockQty());

                productList.add(product);

                logger.debug("Product added to batch list: productName={}",
                        productRequest.getProductName());
            } else {
                logger.warn("Skipping product due to invalid categoryId={}",
                        productRequest.getCategoryId());
            }
        });

        logger.info("Saving {} valid products", productList.size());

        return new ResponseEntity<>(productRepo.saveAll(productList), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> fetchAllProductByCategory(int categoryId) {

        logger.info("Fetch products by category request received: categoryId={}",
                categoryId);

        Category category = categoryRepo.findById(categoryId).orElseThrow(()->{
            logger.warn("Invalid category while fetching products. categoryId={}",
                    categoryId);
            return new CategoryNotFoundException("Category Not Found");
        });

        logger.info("Fetching products for categoryId={}", categoryId);
        List<Product> allByCategory = productRepo.findAllByCategory(category)
                                                .stream()
                                                .sorted(Comparator.comparing(Product::getProductPrice)).toList();
        return new ResponseEntity<>(allByCategory, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> fetchProductById(int productId) {

        logger.info("Fetch product by id request received: productId={}", productId);

        return new ResponseEntity<>(productRepo.findById(productId).orElseThrow(
                ()->{
                    logger.warn("Product Not Found for productID = {} ",productId);
                 return new ProductNotFoundException("Product Not Found");
                }
        ),HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> updateProduct(int productId, ProductRequest productRequest) {

        logger.info("Update product request received: productId={}, productName={}",
                productId, productRequest.getProductName());

        Product product = productRepo.findById(productId).orElseThrow(()->{
            logger.warn("Product Not Found for productID = {} ",productId);
           return new ProductNotFoundException("Product Not Found");
        });

        product.setProductName(productRequest.getProductName());
        product.setProductPrice(productRequest.getProductPrice());
        product.setStockQty(productRequest.getStockQty());

        Product updatedProduct = productRepo.save(product);

        return new ResponseEntity<>(updatedProduct,HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> fetchAllProduct() {

        logger.info("Fetch all products request received");
        List<Product> products = productRepo.findAll().stream().sorted(Comparator.comparing(Product::getProductId)).toList();
        return new ResponseEntity<>(products,HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> deleteProduct(int productId) {

        logger.info("Delete product request received for productId= {}",productId);
        productRepo.findById(productId).orElseThrow(
                ()->{
                    logger.warn("Delete Product not found for productId = {}",productId);
                    return new ProductNotFoundException("Product Not Found");
                }
        );

        productRepo.deleteById(productId);
        logger.info("Product Delete Successfully for productId = {}",productId);
        return new ResponseEntity<>("Product Delete Successfully",HttpStatus.OK);
    }
}
