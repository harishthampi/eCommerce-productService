package dev.harish.productservice.services;

import dev.harish.productservice.dtos.GenericProductDto;
import dev.harish.productservice.models.Product;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProductService {
    GenericProductDto getProductById(Long id);
    GenericProductDto createProduct(GenericProductDto product);
    String deleteProductById (Long id);
    List<GenericProductDto> getAllProducts();
}
