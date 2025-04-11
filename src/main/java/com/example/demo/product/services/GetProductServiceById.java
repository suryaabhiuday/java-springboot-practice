package com.example.demo.product.services;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.demo.product.model.Product;
import com.example.demo.exceptions.ProductNotFoundException;
import com.example.demo.product.ProductRepository;
import com.example.demo.product.model.ProductDTO;

@Service
public class GetProductServiceById implements Query<Integer, ProductDTO> {
    
    private ProductRepository productRepository;

    public GetProductServiceById(ProductRepository productRepository){
        this.productRepository = productRepository;
    }

    @Override
    public ResponseEntity<ProductDTO> execute(Integer id) {
        
        Optional<Product> product = productRepository.findById(id);
        if(product.isPresent()){
            return ResponseEntity.status(HttpStatus.OK).body(new ProductDTO(product.get()));
        }

        throw new ProductNotFoundException(); 
    }
}
