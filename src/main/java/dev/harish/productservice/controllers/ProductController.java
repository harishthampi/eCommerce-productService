package dev.harish.productservice.controllers;

import dev.harish.productservice.dtos.GenericProductDto;
import dev.harish.productservice.services.ProductService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {
     private ProductService productService;
     public ProductController(@Qualifier("fakeStoreProductService") ProductService productService){
         this.productService = productService;//dependency injection via constructor
     }

    @GetMapping
    public List<GenericProductDto> getAllProducts(){
         return productService.getAllProducts();
    }
    @GetMapping("{id}")
    public GenericProductDto getProductById(@PathVariable("id") Long id ){
        return productService.getProductById(id);
    }

    @DeleteMapping("{id}")
    public String deleteProductById(@PathVariable("id") Long id){
         return productService.deleteProductById(id);

    }
    
    @PostMapping
    public GenericProductDto createProduct(@RequestBody GenericProductDto product){
         return productService.createProduct(product);
    }

    @PutMapping("{id}")
    public void updateProductById(){

    }
}
