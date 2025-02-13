package dev.harish.productservice.services;

import dev.harish.productservice.Clients.productService.fakeStrore.FakeStoreProductServiceClient;
import dev.harish.productservice.dtos.GenericProductDto;
import dev.harish.productservice.Clients.productService.fakeStrore.FakeStoreProductDto;
import dev.harish.productservice.exceptions.NotFoundException;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service("fakeStoreProductService")
public class FakeStoreProductService implements ProductService{
    private final FakeStoreProductServiceClient fakeStoreProductServiceClient;
    RestTemplateBuilder restTemplateBuilder;
    private String productSpecificRequestUrl = "https://fakestoreapi.com/products/{id}";
    private String createProductRequestUrl = "https://fakestoreapi.com/products";
    private String getAllProductsRequestUrl =  "https://fakestoreapi.com/products";
    private GenericProductDto convertFakeStoreProductIntoGenericProduct(FakeStoreProductDto fakeStoreProductDto) {
        GenericProductDto genericProduct = new GenericProductDto();
        GenericProductDto product = new GenericProductDto();
        product.setId(fakeStoreProductDto.getId());
        product.setImage(fakeStoreProductDto.getImage());
        product.setDescription(fakeStoreProductDto.getDescription());
        product.setTitle(fakeStoreProductDto.getTitle());
        product.setPrice(fakeStoreProductDto.getPrice());
        product.setCategory(fakeStoreProductDto.getCategory());
        return product;
    }
    public FakeStoreProductService (FakeStoreProductServiceClient fakeStoreProductServiceClient){
        this.fakeStoreProductServiceClient = fakeStoreProductServiceClient;
    }

    @Override
    public GenericProductDto getProductById(Long id) throws NotFoundException {
        return convertFakeStoreProductIntoGenericProduct(fakeStoreProductServiceClient.getProductById(id));
    }

    public GenericProductDto createProduct(GenericProductDto product){
        return convertFakeStoreProductIntoGenericProduct(fakeStoreProductServiceClient.createProduct(product));
    }

    @Override
    public GenericProductDto deleteProductById(Long id) {
        return convertFakeStoreProductIntoGenericProduct(fakeStoreProductServiceClient.deleteProductById(id));
    }

    public List<GenericProductDto> getAllProducts(){
        List<GenericProductDto> genericProductList = new ArrayList<>();
        for(FakeStoreProductDto fakeStoreProduct: fakeStoreProductServiceClient.getAllProducts()){
            genericProductList.add(convertFakeStoreProductIntoGenericProduct(fakeStoreProduct));
        }
        return genericProductList;
    }

    @Override
    public GenericProductDto updateProductById(GenericProductDto product,Long id) {
        return convertFakeStoreProductIntoGenericProduct(fakeStoreProductServiceClient.updateProductById(product,id));
    }


}
