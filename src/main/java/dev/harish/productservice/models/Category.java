package dev.harish.productservice.models;

import jakarta.persistence.Entity;

@Entity
public class Category extends BaseModel{
    private String title;
}
