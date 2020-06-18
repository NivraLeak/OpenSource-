package com.acme.ideogo.controller;


import com.acme.ideogo.model.Category;
import com.acme.ideogo.model.Project;
import com.acme.ideogo.resource.ProjectResource;;
import com.acme.ideogo.resource.SaveProjectResource;
import com.acme.ideogo.service.ProjectService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@Tag(name = "projects", description = "the Projects API")
@RestController
@RequestMapping("/api")
public class ProjectController {

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private ProjectService projectService;

    @Operation(summary = "Get Projects", description = "Get All Projects by Pages", tags = { "projects" })
    @GetMapping("/projects")
    public Page<ProjectResource> getAllProjects(
            @Parameter(description="Pageable Parameter")
                    Pageable pageable) {
        Page<Project> projectsPage = projectService.getAllProjects(pageable);
        List<ProjectResource> resources = projectsPage.getContent().stream().map(this::convertToResource).collect(Collectors.toList());
        return new PageImpl<>(resources, pageable, resources.size());
    }

    @Operation(summary = "Get Project by Id", description = "Get a Project by specifying Id", tags = { "projects" })
    @GetMapping("/projects/{id}")
    public ProjectResource getProjectById(
            @Parameter(description="Project Id")
            @PathVariable(name = "id") Long projectId) {
        return convertToResource(projectService.getProjectById(projectId));
    }

    @PostMapping("/projects")
    public ProjectResource createProject(@Valid @RequestBody SaveProjectResource resource)  {
        Project projects = convertToEntity(resource);
        return convertToResource(projectService.createProject(projects));
    }

    @PutMapping("/projects/{id}")
    public ProjectResource updateProject(@PathVariable(name = "id") Long projectId, @Valid @RequestBody SaveProjectResource resource) {
        Project project = convertToEntity(resource);
        return convertToResource(projectService.updateProject(projectId, project));
    }

    @DeleteMapping("/projects/{id}")
    public ResponseEntity<?> deleteProject(@PathVariable(name = "id") Long projectId) {
        return projectService.deleteProject(projectId);
    }

    @GetMapping("/tags/{tagId}/projects")
    public Page<ProjectResource> getAllProjectsByTagId(@PathVariable(name = "projectId") Long projectId, Pageable pageable) {
        Page<Project> projectsPage = projectService.getAllProjectsByTagId(projectId, pageable);
        List<ProjectResource> resources = projectsPage.getContent().stream().map(this::convertToResource).collect(Collectors.toList());
        return new PageImpl<>(resources, pageable, resources.size());
    }

    private Project convertToEntity(SaveProjectResource resource) {
        return mapper.map(resource, Project.class);
    }

    private ProjectResource convertToResource(Project entity) {
        return mapper.map(entity, ProjectResource.class);
    }

}
