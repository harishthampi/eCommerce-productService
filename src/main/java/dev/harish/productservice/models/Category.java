package dev.harish.productservice.models;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
public class Category extends BaseModel{
    private String name;
    @OneToMany(mappedBy = "category")
    private List<Product> products;

    public void setValue(String name) {
        this.name = name;
    }
    //this is the same relation mapped by category attribute in the other class (Product)
}
