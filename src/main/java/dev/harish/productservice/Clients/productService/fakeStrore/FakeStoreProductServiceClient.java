package dev.harish.productservice.Clients.productService.fakeStrore;

import dev.harish.productservice.dtos.GenericProductDto;
import dev.harish.productservice.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
public class FakeStoreProductServiceClient{
    RestTemplateBuilder restTemplateBuilder;
    @Value("${fakeStore.api.url}")
    private String fakeStoreUrl;

    @Value("${fakeStore.api.paths.products}")
    private String fakeStoreProductApiPath;
    private String productSpecificRequestUrl;
    private String productRequestUrl;




    public FakeStoreProductServiceClient(RestTemplateBuilder restTemplateBuilder, @Value("${fakeStore.api.url}") String fakeStoreUrl,@Value("${fakeStore.api.paths.products}")String fakeStoreProductApiPath){
        this.restTemplateBuilder = restTemplateBuilder;
        this.productSpecificRequestUrl = fakeStoreUrl+fakeStoreProductApiPath+"/{id}";
        this.productRequestUrl = fakeStoreUrl+fakeStoreProductApiPath;
    }



    public FakeStoreProductDto getProductById(Long id) throws NotFoundException {
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDto> response = restTemplate.getForEntity(productSpecificRequestUrl, FakeStoreProductDto.class,id);
        if( response.getBody() == null){
            throw new NotFoundException("Product with id:"+ id + " doesn't exist.");
        }
        return response.getBody();
    }

    public FakeStoreProductDto createProduct(GenericProductDto product){
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDto> response = restTemplate.postForEntity(productRequestUrl,product,FakeStoreProductDto.class);
        return response.getBody();
    }


    public FakeStoreProductDto deleteProductById(Long id) {
        RestTemplate restTemplate = restTemplateBuilder.build();
        RequestCallback requestCallback = restTemplate.acceptHeaderRequestCallback(FakeStoreProductDto.class);
        ResponseExtractor<ResponseEntity<FakeStoreProductDto>> responseExtractor = restTemplate.responseEntityExtractor(FakeStoreProductDto.class);
        ResponseEntity<FakeStoreProductDto> response = restTemplate.execute(productSpecificRequestUrl, HttpMethod.DELETE, requestCallback, responseExtractor, id);
        FakeStoreProductDto fakeStoreProductDto = response.getBody();
        return fakeStoreProductDto;
    }

    public List<FakeStoreProductDto> getAllProducts(){
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDto[]> response = restTemplate.getForEntity(productRequestUrl, FakeStoreProductDto[].class);
        return Arrays.stream(response.getBody()).toList();
    }


    public FakeStoreProductDto updateProductById(GenericProductDto product,Long id) {
        RestTemplate restTemplate = restTemplateBuilder.build();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        // Wrap product inside HttpEntity
        HttpEntity<GenericProductDto> requestEntity = new HttpEntity<>(product, headers);

        ResponseEntity<FakeStoreProductDto> response = restTemplate.exchange(
                productSpecificRequestUrl,  // URL with placeholder
                HttpMethod.PUT,             // HTTP method
                requestEntity,              // Request entity (product as JSON)
                FakeStoreProductDto.class,  // Expected response type
                id                          // URI variable (replaces `{id}` in URL)
        );

        FakeStoreProductDto fakeStoreProductDto = response.getBody();
        return fakeStoreProductDto;
    }

}
