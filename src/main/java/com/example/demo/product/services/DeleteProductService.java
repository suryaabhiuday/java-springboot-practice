package com.example.demo.product.services;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.demo.exceptions.ProductNotFoundException;
import com.example.demo.product.ProductRepository;
import com.example.demo.product.model.Product;

@Service
public class DeleteProductService implements Command<Integer, Void>{
   
   private final ProductRepository productRepository;

   public DeleteProductService (ProductRepository productRepository){
      this.productRepository = productRepository;
   }

   @Override
   public ResponseEntity<Void> execute(Integer input){
      Optional<Product> productOptional = productRepository.findById(input);
      if (productOptional.isPresent()) {
         productRepository.deleteById(input);
         return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
      }
      
      throw new ProductNotFoundException();
      
   } 
}
