package dev.harish.productservice.services;

import dev.harish.productservice.dtos.GenericProductDto;
import dev.harish.productservice.dtos.fakeStoreProductDto;
import dev.harish.productservice.models.Product;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.ResponseExtractor;
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
    private String deleteProductRequestUrl =  "https://fakestoreapi.com/products/{id}";
    public FakeStoreProductService (RestTemplateBuilder restTemplateBuilder){
        this.restTemplateBuilder = restTemplateBuilder;
    }

    private GenericProductDto convertFakeStoreProductIntoGenericProduct(fakeStoreProductDto fakeStoreProductDto) {
        GenericProductDto genericProduct = new GenericProductDto();
        GenericProductDto product = new GenericProductDto();
        product.setImage(fakeStoreProductDto.getImage());
        product.setDescription(fakeStoreProductDto.getDescription());
        product.setTitle(fakeStoreProductDto.getTitle());
        product.setPrice(fakeStoreProductDto.getPrice());
        return product;
    }
    @Override
    public GenericProductDto getProductById(Long id) {
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<fakeStoreProductDto> response = restTemplate.getForEntity(getProductRequestUrl,fakeStoreProductDto.class,id);
        fakeStoreProductDto fakeStoreProductDto = response.getBody();
        return convertFakeStoreProductIntoGenericProduct(fakeStoreProductDto);
    }

    public GenericProductDto createProduct(GenericProductDto product){
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<GenericProductDto> response = restTemplate.postForEntity(createProductRequestUrl,product,GenericProductDto.class);
        return response.getBody();
    }

    @Override
    public GenericProductDto deleteProductById(Long id) {
        RestTemplate restTemplate = restTemplateBuilder.build();
        RequestCallback requestCallback = restTemplate.acceptHeaderRequestCallback(fakeStoreProductDto.class);
        ResponseExtractor<ResponseEntity<fakeStoreProductDto>> responseExtractor = restTemplate.responseEntityExtractor(fakeStoreProductDto.class);
        ResponseEntity<fakeStoreProductDto> response = restTemplate.execute(deleteProductRequestUrl, HttpMethod.DELETE, requestCallback, responseExtractor, id);
        fakeStoreProductDto fakeStoreProductDto = response.getBody();
        return convertFakeStoreProductIntoGenericProduct(fakeStoreProductDto);
    }

    public List<GenericProductDto> getAllProducts(){
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<fakeStoreProductDto[]> response = restTemplate.getForEntity(getAllProductsRequestUrl,fakeStoreProductDto[].class);
        List<GenericProductDto> genericProductList = new ArrayList<>();
        for(fakeStoreProductDto fakeStoreProduct:Arrays.stream(response.getBody()).toList()){
            genericProductList.add(convertFakeStoreProductIntoGenericProduct(fakeStoreProduct));
        }
        return genericProductList;
    }


}
