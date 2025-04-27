package dev.harish.productservice.repository;

import dev.harish.productservice.dtos.GenericProductDto;
import dev.harish.productservice.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ProductRepository extends JpaRepository<Product, UUID> {

}
