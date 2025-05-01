package dev.harish.productservice.services;
import dev.harish.productservice.Clients.productService.fakeStrore.FakeStoreProductDto;
import dev.harish.productservice.dtos.GenericProductDto;
import dev.harish.productservice.exceptions.NotFoundException;
import dev.harish.productservice.models.Category;
import dev.harish.productservice.models.Product;
import dev.harish.productservice.repository.ProductRepository;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Service("selfProductService")
public class SelfProductService implements ProductService{

    private ProductRepository productRepository;

    public SelfProductService(ProductRepository productRepository){

        this.productRepository = productRepository;
    }

    @Override
    public Product getProductById(Long id) throws NotFoundException  {
         Optional<Product> optionalProduct = productRepository.findById(id);
        if (optionalProduct.isEmpty()) {
            throw new NotFoundException ("Product with id: " + id  + " doesn't exist.");
        }

        return optionalProduct.get();
    }

    @Override
    public Product createProduct(GenericProductDto product) {
        return productRepository.save(product);
    }

    @Override
    public void deleteProductById(Long id) {

        productRepository.deleteById(id);
    }

    @Override
    public Page<Product> getAllProducts(int pageNumber, int pageSize) {

        return productRepository.findAll(
                PageRequest.of(pageNumber, pageSize)
        );
    }

    @Override
    public Product updateProductById(GenericProductDto product, Long id) {
        return null;
    }
}
