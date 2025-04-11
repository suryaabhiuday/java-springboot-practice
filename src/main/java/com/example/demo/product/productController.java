package com.example.demo.product;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.product.model.Product;
import com.example.demo.product.model.ProductDTO;
import com.example.demo.product.model.UpdateProductCommand;
import com.example.demo.product.services.DeleteProductService;
import com.example.demo.product.services.GetProductService;
import com.example.demo.product.services.GetProductServiceById;
import com.example.demo.product.services.PutProductService;
import com.example.demo.product.services.createProductService;

@RestController
public class productController {

    private createProductService cps;

    private GetProductService gps; 

    private PutProductService pps;

    private DeleteProductService dps;

    private GetProductServiceById gpsi;
    
    public productController(createProductService cps,
                            GetProductService gps,
                            PutProductService pps,
                            DeleteProductService dps,
                            GetProductServiceById gpsi){

        this.cps = cps;
        this.gps = gps;
        this.pps = pps;
        this.dps = dps;
        this.gpsi = gpsi;
    }


    @PostMapping("/product")
    public ResponseEntity<ProductDTO> createProduct(@RequestBody Product product){
        return cps.execute(product);
    }

    @GetMapping("/product")
    public ResponseEntity<List<ProductDTO>> getProduct(){
        return gps.execute(null);
    }

    @GetMapping("/product/{id}")
    public ResponseEntity<ProductDTO> getProductById(@PathVariable Integer id){
        return gpsi.execute(id);
    }

    @PutMapping("/product/{id}")
    public ResponseEntity<ProductDTO> putProduct(@PathVariable Integer id, @RequestBody Product product){
        return pps.execute(new UpdateProductCommand(id, product));
    }

    @DeleteMapping("/product/{id}")
    public ResponseEntity<Void> deleteProduct (@PathVariable Integer id){
        return dps.execute(id);
    }
}