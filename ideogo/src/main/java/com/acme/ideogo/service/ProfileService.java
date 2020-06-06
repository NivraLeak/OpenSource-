package com.acme.ideogo.service;

import com.acme.ideogo.model.Profile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.ResponseEntity;

public interface ProfileService  {
    Page<Profile> getAllProfilesByTagId(Long tagId, Pageable pageable);
    ResponseEntity<?> deleteProfile(Long profileId);
    Profile updateProfile(Long postId, Profile postRequest);
    Profile createProfile(Profile profile);
    Profile getProfileById(Long postId);
    Page<Profile> getAllProfiles(Pageable pageable);
    Profile assignProfileTag(Long profileId, Long tagId);
    Profile unassignProfileTag(Long profileId, Long tagId);
}
