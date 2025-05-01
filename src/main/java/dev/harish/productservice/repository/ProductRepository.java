package dev.harish.productservice.repository;

import dev.harish.productservice.dtos.GenericProductDto;
import dev.harish.productservice.models.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import org.springframework.data.domain.Pageable;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    Optional<Product> findById(Long productId);
    Product save(GenericProductDto product);

    void deleteById(Long id);

    Page<Product> findAll(Pageable pageable);

}
