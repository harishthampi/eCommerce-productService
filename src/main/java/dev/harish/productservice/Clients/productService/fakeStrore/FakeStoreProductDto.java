package dev.harish.productservice.Clients.productService.fakeStrore;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
@Setter
@Getter
public class FakeStoreProductDto {
    @JsonProperty("id")
    private Long id;
    @JsonProperty("title")
    private String title;
    @JsonProperty("price")
    private double price;
    @JsonProperty("category")
    private String category;
    @JsonProperty("description")
    private String description;
    @JsonProperty("image")
    private String image;
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public  String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public  double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }

    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }

    public  String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public  String getImage() { return image; }
    public void setImage(String image) { this.image = image; }
}
