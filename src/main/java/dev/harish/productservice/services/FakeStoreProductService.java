package dev.harish.productservice.services;

import dev.harish.productservice.Clients.productService.fakeStrore.FakeStoreProductServiceClient;
import dev.harish.productservice.dtos.GenericProductDto;
import dev.harish.productservice.Clients.productService.fakeStrore.FakeStoreProductDto;
import dev.harish.productservice.exceptions.NotFoundException;
import dev.harish.productservice.models.Category;
import dev.harish.productservice.models.Product;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service("fakeStoreProductService")
public class FakeStoreProductService implements ProductService{
    private final FakeStoreProductServiceClient fakeStoreProductServiceClient;
    private String productSpecificRequestUrl = "https://fakestoreapi.com/products/{id}";
    private String createProductRequestUrl = "https://fakestoreapi.com/products";
    private String getAllProductsRequestUrl =  "https://fakestoreapi.com/products";
    Product convertFakeStoreProductIntoGenericProduct(FakeStoreProductDto fakeStoreProductDto) {
        Product product = new Product();
        product.setCategory(new Category());
        //product.setId(fakeStoreProductDto.getId());
        product.setImage(fakeStoreProductDto.getImage());
        product.setDescription(fakeStoreProductDto.getDescription());
        product.setTitle(fakeStoreProductDto.getTitle());
        product.setPrice(fakeStoreProductDto.getPrice());
        product.getCategory().setValue(fakeStoreProductDto.getCategory());
        return product;
    }
    public FakeStoreProductService (FakeStoreProductServiceClient fakeStoreProductServiceClient){
        this.fakeStoreProductServiceClient = fakeStoreProductServiceClient;
    }

    @Override
    public Product getProductById(Long id) throws NotFoundException {
        return convertFakeStoreProductIntoGenericProduct(fakeStoreProductServiceClient.getProductById(id));
    }

    public Product createProduct(GenericProductDto product){
        return convertFakeStoreProductIntoGenericProduct(fakeStoreProductServiceClient.createProduct(product));
    }

    @Override
    public void deleteProductById(Long id) {
        //return convertFakeStoreProductIntoGenericProduct(fakeStoreProductServiceClient.deleteProductById(id));
    }


    public Page<Product> getAllProducts(int pageNumber, int pageSize){
        List<Product> products = new ArrayList<>();
        for(FakeStoreProductDto fakeStoreProduct: fakeStoreProductServiceClient.getAllProducts()){
            products.add(convertFakeStoreProductIntoGenericProduct(fakeStoreProduct));
        }
        return  new PageImpl<>(products);
    }

    @Override
    public Product updateProductById(GenericProductDto product,Long id) {
        return convertFakeStoreProductIntoGenericProduct(fakeStoreProductServiceClient.updateProductById(product,id));
    }


}
