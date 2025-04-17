package com.example.demo.product.services;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.demo.exceptions.ProductNotValidException;
import com.example.demo.product.ProductRepository;
import com.example.demo.product.model.Product;
import com.example.demo.product.model.ProductDTO;
import com.mysql.cj.util.StringUtils;

import ch.qos.logback.core.util.StringUtil;

@Service
public class createProductService implements Command<Product, ProductDTO> {

    private ProductRepository productRepository;

    public createProductService (ProductRepository productRepository){
        this.productRepository = productRepository;
    }

    @Override
    public ResponseEntity<ProductDTO> execute(Product product) {
        if (StringUtils.isNullOrEmpty(product.getName())){
            throw new ProductNotValidException();
        }

        if (product.getDescription().length() < 20) {
            throw new ProductNotValidException();
        }

        if (product.getPrice() <= 0.0) {
            throw new ProductNotValidException();
        }
        Product savedProduct = productRepository.save(product);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ProductDTO(savedProduct));
    }
}
