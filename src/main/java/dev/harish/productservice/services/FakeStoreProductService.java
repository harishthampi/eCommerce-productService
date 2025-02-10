package dev.harish.productservice.services;

import dev.harish.productservice.dtos.GenericProductDto;
import dev.harish.productservice.dtos.fakeStoreProductDto;
import dev.harish.productservice.models.Product;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.*;
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
    private String productSpecificRequestUrl = "https://fakestoreapi.com/products/{id}";
    private String createProductRequestUrl = "https://fakestoreapi.com/products";
    private String getAllProductsRequestUrl =  "https://fakestoreapi.com/products";

    public FakeStoreProductService (RestTemplateBuilder restTemplateBuilder){
        this.restTemplateBuilder = restTemplateBuilder;
    }

    private GenericProductDto convertFakeStoreProductIntoGenericProduct(fakeStoreProductDto fakeStoreProductDto) {
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
    @Override
    public GenericProductDto getProductById(Long id) {
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<fakeStoreProductDto> response = restTemplate.getForEntity(productSpecificRequestUrl,fakeStoreProductDto.class,id);
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
        ResponseEntity<fakeStoreProductDto> response = restTemplate.execute(productSpecificRequestUrl, HttpMethod.DELETE, requestCallback, responseExtractor, id);
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

    @Override
    public GenericProductDto updateProductById(GenericProductDto product,Long id) {
        RestTemplate restTemplate = restTemplateBuilder.build();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        // Wrap product inside HttpEntity
        HttpEntity<GenericProductDto> requestEntity = new HttpEntity<>(product, headers);

        ResponseEntity<fakeStoreProductDto> response = restTemplate.exchange(
                productSpecificRequestUrl,  // URL with placeholder
                HttpMethod.PUT,             // HTTP method
                requestEntity,              // Request entity (product as JSON)
                fakeStoreProductDto.class,  // Expected response type
                id                          // URI variable (replaces `{id}` in URL)
        );

        fakeStoreProductDto fakeStoreProductDto = response.getBody();
        return convertFakeStoreProductIntoGenericProduct(fakeStoreProductDto);
    }


}
