package com.acme.ideogo.service;

import com.acme.ideogo.model.Skill;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface SkillService {

    Page<Skill> getAllSkillsByTagId(Long tagId, Pageable pageable);
    Page<Skill>getAllSkillsByProfileId(Long profileId, Pageable pageable);
    Skill getSkillByIdAndProfileId(Long profileId, Long skillId);
    Skill getSkillByIdAndTagId(Long tagId, Long skillId);
    Skill createSkill(Long tagId, Skill skill);
    Skill updateSkill(Long tagId, Long skillId, Skill skillDetails);
    ResponseEntity<?> deleteSkill(Long tagId, Long skillId);

}
