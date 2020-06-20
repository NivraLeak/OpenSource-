package com.acme.ideogo.controller;

import com.acme.ideogo.model.Skill;
import com.acme.ideogo.model.Subscription;
import com.acme.ideogo.resource.SaveSkillResource;
import com.acme.ideogo.resource.SaveSubscriptionResource;
import com.acme.ideogo.resource.SkillResource;
import com.acme.ideogo.resource.SubscriptionResource;
import com.acme.ideogo.service.SkillService;
import com.acme.ideogo.service.SubscriptionService;
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

@Tag(name = "subscriptions", description = "the Subscriptions API")
@RestController
@RequestMapping("/api")
public class SubscriptionController {

    @Autowired
    private ModelMapper mapper;
    @Autowired
    private SubscriptionService subscriptionService;

    @GetMapping("/tags/{tagId}/skills")
    public Page<SubscriptionResource> getAllSubscriptionsByTagId(
            @PathVariable(name = "tagId") Long tagId,
            Pageable pageable) {
        Page<Subscription> skillPage = subscriptionService.getAllSubscriptionsByMembershipId(tagId, pageable);
        List<SubscriptionResource> resources = skillPage.getContent().stream().map(this::convertToResource).collect(Collectors.toList());
        return new PageImpl<>(resources, pageable, resources.size());
    }

    @GetMapping("/tags/{tagId}/skills/{subscriptionId}")
    public SubscriptionResource getSubscriptionByIdAndMembershipId(@PathVariable(name = "tagId") Long tagId,
                                              @PathVariable(name = "subscriptionId") Long subscriptionId) {
        return convertToResource(subscriptionService.getSubscriptionByIdAndMembershipId(tagId, subscriptionId));
    }

    @PostMapping("/tags/{tagId}/skills")
    public SubscriptionResource createSkill(@PathVariable(name = "tagId") Long TagId,
                                     @Valid @RequestBody SaveSubscriptionResource resource) {
        return convertToResource(subscriptionService.createSubscription(TagId, convertToEntity(resource)));

    }

    @PutMapping("/tags/{tagId}/skills/{skillId}")
    public SubscriptionResource updateSkill(@PathVariable(name = "tagId") Long postId,
                                     @PathVariable(name = "skillId") Long commentId,
                                     @Valid @RequestBody SaveSubscriptionResource resource) {
        return convertToResource(subscriptionService.updateSubscription(postId, commentId, convertToEntity(resource)));
    }

    @DeleteMapping("/tags/{tagId}/skills/{skillId}")
    public ResponseEntity<?> deleteSkill(@PathVariable(name = "tagId") Long postId,
                                         @PathVariable(name = "skillId") Long commentId) {
        return subscriptionService.deleteSubscription(postId, commentId);
    }

    private Subscription convertToEntity(SaveSubscriptionResource resource) {
        return mapper.map(resource, Subscription.class);
    }

    private SubscriptionResource convertToResource(Subscription entity) {
        return mapper.map(entity, SubscriptionResource.class);
    }




}
