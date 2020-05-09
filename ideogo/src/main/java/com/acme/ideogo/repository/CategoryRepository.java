package com.acme.ideogo.repository;

import com.acme.ideogo.model.Category;
import com.acme.ideogo.model.Project;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category,Long> {
    //Page<Project>FindByCategoryId(Long CategoryId, Pageable pageable);
    //Page<Project>FindByIdAndCategoryId(Long id, Long categoryId);

}

