package dev.harish.productservice.services;

import dev.harish.productservice.dtos.GenericProductDto;
import dev.harish.productservice.dtos.fakeStoreProductDto;
import dev.harish.productservice.models.Product;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service("fakeStoreProductService")
public class FakeStoreProductService implements ProductService{
    RestTemplateBuilder restTemplateBuilder;
    private String getProductRequestUrl = "https://fakestoreapi.com/products/{id}";
    private String createProductRequestUrl = "https://fakestoreapi.com/products";
    private String getAllProductsRequestUrl =  "https://fakestoreapi.com/products";
    public FakeStoreProductService (RestTemplateBuilder restTemplateBuilder){
        this.restTemplateBuilder = restTemplateBuilder;
    }
    @Override
    public GenericProductDto getProductById(Long id) {
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<fakeStoreProductDto> response = restTemplate.getForEntity(getProductRequestUrl,fakeStoreProductDto.class,id);
        fakeStoreProductDto fakeStoreProductDto = response.getBody();
        GenericProductDto product = new GenericProductDto();
         product.setImage(fakeStoreProductDto.getImage());
         product.setDescription(fakeStoreProductDto.getDescription());
         product.setTitle(fakeStoreProductDto.getTitle());
         product.setPrice(fakeStoreProductDto.getPrice());
        return product;
    }

    public GenericProductDto createProduct(GenericProductDto product){
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<GenericProductDto> response = restTemplate.postForEntity(createProductRequestUrl,product,GenericProductDto.class);
        return response.getBody();
    }

    @Override
    public String deleteProductById(Long id) {
        return "deleted";
    }

    public List<GenericProductDto> getAllProducts(){
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<fakeStoreProductDto[]> response = restTemplate.getForEntity(getAllProductsRequestUrl,fakeStoreProductDto[].class);
        List<GenericProductDto> genericProductList = new ArrayList<>();
        for(fakeStoreProductDto fakeStoreProduct:Arrays.stream(response.getBody()).toList()){
            GenericProductDto genericProduct = new GenericProductDto();
            genericProduct.setImage(fakeStoreProduct.getImage());
            genericProduct.setDescription(fakeStoreProduct.getDescription());
            genericProduct.setTitle(fakeStoreProduct.getTitle());
            genericProduct.setPrice(fakeStoreProduct.getPrice());
            genericProduct.setCategory(fakeStoreProduct.getCategory());
            genericProductList.add(genericProduct);
        }
        return genericProductList;
    }


}
