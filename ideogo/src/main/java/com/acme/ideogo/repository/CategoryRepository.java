package com.acme.ideogo.repository;

import com.acme.ideogo.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
