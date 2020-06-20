package com.acme.ideogo.controller;


import com.acme.ideogo.model.Profile;
import com.acme.ideogo.resource.ProfileResource;
import com.acme.ideogo.resource.SaveProfileResource;
import com.acme.ideogo.service.ProfileService;
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

@Tag(name = "profiles", description = "the Profiles API")
@RestController
@RequestMapping("/api")
public class ProfileController {

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private ProfileService profileService;

    @Operation(summary = "Get Profiles", description = "Get All Profiles by Pages", tags = { "profiles" })
    @GetMapping("/profiles")
    public Page<ProfileResource> getAllProfiles(
            @Parameter(description="Pageable Parameter")
                    Pageable pageable) {
        Page<Profile> profilesPage = profileService.getAllProfiles(pageable);
        List<ProfileResource> resources = profilesPage.getContent().stream().map(this::convertToResource).collect(Collectors.toList());
        return new PageImpl<>(resources, pageable, resources.size());
    }

    @Operation(summary = "Get Profile by Id", description = "Get a Profile by specifying Id", tags = { "profiles" })
    @GetMapping("/profiles/{id}")
    public ProfileResource getProfileById(
            @Parameter(description="Profile Id")
            @PathVariable(name = "id") Long profileId) {
        return convertToResource(profileService.getProfileById(profileId));
    }

    @PostMapping("/profiles")
    public ProfileResource createProfile(@Valid @RequestBody SaveProfileResource resource)  {
        Profile profile = convertToEntity(resource);
        return convertToResource(profileService.createProfile(profile));
    }

    @PutMapping("/profiles/{id}")
    public ProfileResource updateProfile(@PathVariable(name = "id") Long profileId, @Valid @RequestBody SaveProfileResource resource) {
        Profile profile = convertToEntity(resource);
        return convertToResource(profileService.updateProfile(profileId, profile));
    }

    @DeleteMapping("/profiles/{id}")
    public ResponseEntity<?> deleteProfile(@PathVariable(name = "id") Long profileId) {
        return profileService.deleteProfile(profileId);
    }

    @GetMapping("/tags/{tagId}/profiles")
    public Page<ProfileResource> getAllProfileByTagId(@PathVariable(name = "tagId") Long tagId, Pageable pageable) {
        Page<Profile> profilesPage = profileService.getAllProfilesByTagId(tagId, pageable);
        List<ProfileResource> resources = profilesPage.getContent().stream().map(this::convertToResource).collect(Collectors.toList());
        return new PageImpl<>(resources, pageable, resources.size());
    }

    @PostMapping("/profiles/{profileId}/tags/{tagId}")
    public ProfileResource assignProfileTag(@PathVariable(name = "profileId") Long profileId,
                                      @PathVariable(name = "tagId") Long tagId) {
        return convertToResource(profileService.assignProfileTag(profileId, tagId));
    }

    @DeleteMapping("/profiles/{profileId}/tags/{tagId}")
    public ProfileResource unassignProfileTag(@PathVariable(name = "profileId") Long profileId,
                                        @PathVariable(name = "tagId") Long tagId) {

        return convertToResource(profileService.unassignProfileTag(profileId, tagId));
    }

    private Profile convertToEntity(SaveProfileResource resource) {
        return mapper.map(resource, Profile.class);
    }

    private ProfileResource convertToResource(Profile entity) {
        return mapper.map(entity, ProfileResource.class);
    }

}
