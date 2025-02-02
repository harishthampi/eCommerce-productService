package dev.harish.productservice.services;

import dev.harish.productservice.dtos.GenericProductDto;
import dev.harish.productservice.dtos.fakeStoreProductDto;
import dev.harish.productservice.models.Product;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service("fakeStoreProductService")
public class FakeStoreProductService implements ProductService{
    RestTemplateBuilder restTemplateBuilder;
    private String getProductRequestUrl = "https://fakestoreapi.com/products/{id}";
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
}
