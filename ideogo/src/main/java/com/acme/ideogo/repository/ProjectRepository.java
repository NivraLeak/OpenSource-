package com.acme.ideogo.repository;

import com.acme.ideogo.model.Project;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectRepository extends JpaRepository<Project, Long> {
}
