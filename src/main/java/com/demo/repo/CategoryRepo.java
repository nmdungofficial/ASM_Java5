package com.demo.repo;

import com.demo.model.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.awt.print.Pageable;
import java.util.List;

public interface CategoryRepo extends JpaRepository<Category, String> {
    @Query("SELECT c FROM Category c WHERE c.name LIKE %?1% OR c.id LIKE %?1%")
    List<Category> findAll(String keyword, Sort sort);
}
