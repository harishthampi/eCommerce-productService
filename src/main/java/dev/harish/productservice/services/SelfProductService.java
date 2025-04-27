package dev.harish.productservice.services;
import dev.harish.productservice.Clients.productService.fakeStrore.FakeStoreProductDto;
import dev.harish.productservice.dtos.GenericProductDto;
import dev.harish.productservice.exceptions.NotFoundException;
import dev.harish.productservice.models.Product;
import dev.harish.productservice.repository.ProductRepository;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


//@Service("selfProductService")
//public class SelfProductService implements ProductService{
//
//    ProductRepository productRepository;
//
//    public SelfProductService(ProductRepository productRepository , RestTemplateBuilder restTemplateBuilder){
//
//        this.productRepository = productRepository;
//    }
//    @Override
//    public GenericProductDto getProductById(Long id) throws NotFoundException {
//        return productRepository.findById(id);
//    }
//
//    @Override
//    public GenericProductDto createProduct(GenericProductDto product) {
//        return productRepository.save(product);
//    }
//
//    @Override
//    public GenericProductDto deleteProductById(Long id) {
//
//        return productRepository.deleteById(id);
//    }
//
//    @Override
//    public List<GenericProductDto> getAllProducts() {
//        return null;
//    }
//
//    @Override
//    public GenericProductDto updateProductById(GenericProductDto product, Long id) {
//        return null;
//    }
//}
