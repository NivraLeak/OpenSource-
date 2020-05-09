package com.acme.ideogo.repository;

import com.acme.ideogo.model.Project;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProjectRepository extends JpaRepository<Project,Long> {
    Page<Project>findByUserId(Long userId, Pageable pageable);
    Optional<Project>findByIdAndUserId(Long id,Long userId);


}
