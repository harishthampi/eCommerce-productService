package dev.harish.productservice.services;

import dev.harish.productservice.dtos.GenericProductDto;
import dev.harish.productservice.models.Product;
import org.springframework.stereotype.Service;

@Service
public interface ProductService {
    GenericProductDto getProductById(Long id);
}
