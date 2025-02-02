package dev.harish.productservice.controllers;

import dev.harish.productservice.dtos.GenericProductDto;
import dev.harish.productservice.services.ProductService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {
     private ProductService productService;
     public ProductController(@Qualifier("fakeStoreProductService") ProductService productService){
         this.productService = productService;//dependency injection via constructor
     }

    @GetMapping
    public void getAllProducts(){

    }
    @GetMapping("{id}")
    public GenericProductDto getProductById(@PathVariable("id") Long id ){
        return productService.getProductById(id);
    }

    @DeleteMapping("{id}")
    public void deleteProductById(){

    }
    
    @PostMapping
    public void createProduct(){

    }

    @PutMapping("{id}")
    public void updateProductById(){

    }
}
