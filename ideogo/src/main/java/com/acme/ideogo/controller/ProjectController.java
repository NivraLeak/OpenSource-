package com.acme.ideogo.controller;

import com.acme.ideogo.exception.ResourceNotFoundException;
import com.acme.ideogo.model.Project;
import com.acme.ideogo.repository.ProjectRepository;
import com.acme.ideogo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class ProjectController {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProjectRepository projectRepository;

    @GetMapping("/users/{userId}/projects")
    public Page<Project> getAllProjectsByUserId(@PathVariable(value="userId") Long userId, Pageable pageable){
        return projectRepository.findByUserId(userId,pageable);
    }

    @PostMapping("/users/{userId}/projects")
    public Project createProject(@PathVariable(value="userId") Long userId,
                                 @Valid @RequestBody Project project) {
        return userRepository.findById(userId).map(user -> {
            project.setUser(user);
            return projectRepository.save(project);
        }).orElseThrow(()->new ResourceNotFoundException("UserID" + userId+"not found"));
    }

    @PutMapping("/users/{userId}/projects/{projectId}")
    public Project updateProject(@PathVariable( value = "userId") Long userId,
                                 @PathVariable(value = "projectId") Long projectId,
                                 @Valid @RequestBody Project projectdetails){
        if (!userRepository.existsById(userId)) {
            throw  new ResourceNotFoundException("UserId" + userId+"not found");
        }
        return projectRepository.findById(projectId).map(project ->{
            project.setDescription(projectdetails.getDescription());
            return projectRepository.save(project);
        } ).orElseThrow(()->new ResourceNotFoundException("ProjectId" + projectId +"not found"));
    }

}
