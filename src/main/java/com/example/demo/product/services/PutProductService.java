package com.example.demo.product.services;

import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.demo.exceptions.ProductNotFoundException;
import com.example.demo.product.ProductRepository;
import com.example.demo.product.model.Product;
import com.example.demo.product.model.ProductDTO;
import com.example.demo.product.model.UpdateProductCommand;

@Service
public class PutProductService implements Command<UpdateProductCommand, ProductDTO> {

    private ProductRepository productRepository;

    public PutProductService(ProductRepository productRepository){
        this.productRepository = productRepository;
    }

    @Override
    public ResponseEntity<ProductDTO> execute(UpdateProductCommand updateProductCommand){

        Optional<Product> producOptional = productRepository.findById(updateProductCommand.getId());
        if (producOptional.isPresent()) {

            Product product = updateProductCommand.getProduct();
            product.setId(updateProductCommand.getId());
            productRepository.save(product);
            return ResponseEntity.ok(new ProductDTO(product));
            
        }
        
        throw new ProductNotFoundException();
    }
}
