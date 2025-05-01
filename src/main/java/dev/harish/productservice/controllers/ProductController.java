package dev.harish.productservice.controllers;

import dev.harish.productservice.dtos.ExceptionDto;
import dev.harish.productservice.dtos.GenericProductDto;
import dev.harish.productservice.exceptions.NotFoundException;
import dev.harish.productservice.models.Product;
import dev.harish.productservice.services.ProductService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/products")
public class ProductController {
     private ProductService productService;
     public ProductController(@Qualifier("selfProductService") ProductService productService){
         this.productService = productService;//dependency injection via constructor
     }

    @GetMapping
    public Page<Product> getAllProducts(@RequestParam("pageNumber") int pageNumber, @RequestParam("pageSize") int pageSize){
         return productService.getAllProducts(pageNumber, pageSize);
    }
    @GetMapping("{id}")
    public Product getProductById(@PathVariable("id") Long id ) throws NotFoundException{

         return productService.getProductById(id);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Product> deleteProductById(@PathVariable("id") Long id){
         return new ResponseEntity<>( HttpStatus.NOT_FOUND);

    }

    @PostMapping
    public Product createProduct(@RequestBody GenericProductDto product){
         return productService.createProduct(product);
    }

    @PutMapping("{id}")
    public Product updateProductById(@RequestBody GenericProductDto product,@PathVariable("id") Long id){
         return productService.updateProductById(product,id);
    }

}
