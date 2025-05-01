package dev.harish.productservice.services;

import dev.harish.productservice.dtos.GenericProductDto;
import dev.harish.productservice.exceptions.NotFoundException;
import dev.harish.productservice.models.Product;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProductService {
    Product  getProductById(Long id) throws NotFoundException;
    Product  createProduct(GenericProductDto product);
    void  deleteProductById (Long id);
    Page<Product> getAllProducts(int pageNumber, int Size);
    Product  updateProductById(GenericProductDto product,Long id);
}
