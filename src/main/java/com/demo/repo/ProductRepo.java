package com.demo.repo;

import com.demo.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ProductRepo extends JpaRepository<Product, Integer> {
    @Query("SELECT p FROM Product p WHERE p.name LIKE %?1% OR p.category.name LIKE %?1%")
    Page<Product> findBySearch(String keyword, Pageable page);

    @Query("SELECT p FROM Product p WHERE (p.name LIKE %:keyword% OR p.category.name LIKE %:keyword%) AND p.category.id = :categoryId AND p.price BETWEEN :minPrice AND :maxPrice ")
    Page<Product> findAll(String keyword, String categoryId, int minPrice, int maxPrice, Pageable page);

    @Query("SELECT p FROM Product p WHERE (p.name LIKE %?1% OR p.category.name LIKE %?1%) AND p.price BETWEEN ?2 AND ?3")
    Page<Product> findNotCategory(String keyword, int minPrice, int maxPrice, Pageable page);
}
