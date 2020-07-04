package com.acme.ideogo.controller;


import com.acme.ideogo.model.Category;
import com.acme.ideogo.model.User;
import com.acme.ideogo.resource.UserResource;;
import com.acme.ideogo.resource.SaveUserResource;
import com.acme.ideogo.resource.UserResource2;
import com.acme.ideogo.service.ProjectService;
//import com.acme.ideogo.service.UserDetailsServiceImpl;
import com.acme.ideogo.service.UserDetailsServiceImpl;
import com.acme.ideogo.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jdk.nashorn.internal.objects.annotations.Getter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;




import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Tag(name = "users", description = "the Users API")
@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private UserService userService;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;



    @Operation(summary = "Get Users", description = "Get All Users by Pages", tags = { "users" })
    @GetMapping("/users")
    public Page<UserResource> getAllUsers(
            @Parameter(description="Pageable Parameter")
                    Pageable pageable) {
        Page<com.acme.ideogo.model.User> usersPage = userService.getAllUsers(pageable);
        List<UserResource> resources = usersPage.getContent().stream().map(this::convertToResource).collect(Collectors.toList());
        return new PageImpl<>(resources, pageable, resources.size());
    }

    @Operation(summary = "Get User by Id", description = "Get a User by specifying Id", tags = { "users" })
    @GetMapping("/users/{id}")
    public UserResource getUserById(
            @Parameter(description="Project Id")
            @PathVariable(name = "id") Long userId) {
        return convertToResource(userService.getUserById(userId));
    }

    @PostMapping("/users")
    public UserResource createUser(@Valid @RequestBody SaveUserResource resource)  {
        com.acme.ideogo.model.User users = convertToEntity(resource);
        return convertToResource(userService.createUser(users));
    }

    @PutMapping("/users/{id}")
    public UserResource updateUser(@PathVariable(name = "id") Long userId, @Valid @RequestBody SaveUserResource resource) {
        com.acme.ideogo.model.User user = convertToEntity(resource);
        return convertToResource(userService.updateUser(userId, user));
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable(name = "id") Long userId) {
        return userService.deleteUser(userId);
    }


    private com.acme.ideogo.model.User convertToEntity(SaveUserResource resource) {
        return mapper.map(resource, com.acme.ideogo.model.User.class);
    }

    private UserResource convertToResource(com.acme.ideogo.model.User entity) {
        return mapper.map(entity, UserResource.class);
    }




    //////////////////////SECURITY
    @GetMapping
    public List<UserResource2> getAll() {
        return userDetailsService.getAll().stream()
                .map(this::convertToResource2).collect(Collectors.toList());
    }

    private UserResource2 convertToResource2(org.springframework.security.core.userdetails.User entity) {
        return mapper.map(entity, UserResource2.class);
    }

}
