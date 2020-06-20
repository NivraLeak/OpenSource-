package com.acme.ideogo.service;

import com.acme.ideogo.model.Project;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.ResponseEntity;

public interface ProjectService  {
    Page<Project> getAllProjectsByTagId(Long tagId, Pageable pageable);
    ResponseEntity<?> deleteProject(Long postId);
    Project updateProject(Long postId, Project postRequest);
    Project createProject(Project project);
    Project getProjectById(Long postId);
    Page<Project> getAllProjects(Pageable pageable);
}
