package com.acme.ideogo.controller;

import com.acme.ideogo.model.Skill;
import com.acme.ideogo.resource.SaveSkillResource;
import com.acme.ideogo.resource.SkillResource;
import com.acme.ideogo.service.SkillService;
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

@Tag(name = "skills", description = "the Skills API")
@RestController
@RequestMapping("/api")
public class SkillController {
    @Autowired
    private ModelMapper mapper;
    @Autowired
    private SkillService skillService;

    @GetMapping("/tags/{tagId}/skills")
    public Page<SkillResource> getAllSkillsByTagId(
            @PathVariable(name = "tagId") Long tagId,
            Pageable pageable) {
        Page<Skill> skillPage = skillService.getAllSkillsByTagId(tagId, pageable);
        List<SkillResource> resources = skillPage.getContent().stream().map(this::convertToResource).collect(Collectors.toList());
        return new PageImpl<>(resources, pageable, resources.size());
    }

    @GetMapping("/tags/{tagId}/skills/{skillId}")
    public SkillResource getSkillByIdAndTagId(@PathVariable(name = "tagId") Long tagId,
                                                   @PathVariable(name = "skillId") Long skillId) {
        return convertToResource(skillService.getSkillByIdAndTagId(tagId, skillId));
    }

    @PostMapping("/tags/{tagId}/skills")
    public SkillResource createSkill(@PathVariable(name = "tagId") Long TagId,
                                         @Valid @RequestBody SaveSkillResource resource) {
        return convertToResource(skillService.createSkill(TagId, convertToEntity(resource)));

    }

    @PutMapping("/tags/{tagId}/skills/{skillId}")
    public SkillResource updateSkill(@PathVariable(name = "tagId") Long postId,
                                         @PathVariable(name = "skillId") Long commentId,
                                         @Valid @RequestBody SaveSkillResource resource) {
        return convertToResource(skillService.updateSkill(postId, commentId, convertToEntity(resource)));
    }

    @DeleteMapping("/tags/{tagId}/skills/{skillId}")
    public ResponseEntity<?> deleteSkill(@PathVariable(name = "tagId") Long postId,
                                           @PathVariable(name = "skillId") Long commentId) {
        return skillService.deleteSkill(postId, commentId);
    }

    private Skill convertToEntity(SaveSkillResource resource) {
        return mapper.map(resource, Skill.class);
    }

    private SkillResource convertToResource(Skill entity) {
        return mapper.map(entity, SkillResource.class);
    }



}
