package com.acme.ideogo.service;


import com.acme.ideogo.exception.ResourceNotFoundException;
import com.acme.ideogo.model.Category;
import com.acme.ideogo.model.Project;
import com.acme.ideogo.model.Tag;
import com.acme.ideogo.repository.CategoryRepository;
import com.acme.ideogo.repository.ProjectRepository;
import com.acme.ideogo.repository.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectServiceImpl implements ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private TagRepository tagRepository;

    public Page<Project> getAllProjectsByTagId(Long tagId, Pageable pageable) {
        return tagRepository.findById(tagId).map(tag -> {
            List<Project> projects = tag.getProjects();
            int tagsCount = projects.size();
            return new PageImpl<>(projects, pageable, tagsCount);
        })
                .orElseThrow(() -> new ResourceNotFoundException("Project", "Id", tagId));
    }

    @Override
    public ResponseEntity<?> deleteProject(Long projectId) {
        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new ResourceNotFoundException("Project", "Id", projectId));
        projectRepository.delete(project);
        return ResponseEntity.ok().build();
    }

    @Override
    public Project updateProject(Long projectId, Project projectRequest) {
        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new ResourceNotFoundException("Project", "Id", projectId));
        project.setName(projectRequest.getName());
        project.setDescription(projectRequest.getDescription());
        project.setContent(projectRequest.getContent());
        return projectRepository.save(project);
    }

    @Override
    public Project createProject(Project project) {
        return projectRepository.save(project);
    }

    @Override
    public Project getProjectById(Long proyectId) {
        return projectRepository.findById(proyectId)
                .orElseThrow(() -> new ResourceNotFoundException("Project", "Id", proyectId));
    }

    @Override
    public Page<Project> getAllProjects(Pageable pageable) {
        return projectRepository.findAll(pageable);
    }
}
