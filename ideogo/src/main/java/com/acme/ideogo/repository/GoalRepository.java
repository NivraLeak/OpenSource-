package com.acme.ideogo.repository;

import com.acme.ideogo.model.Goal;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GoalRepository extends JpaRepository<Goal, Long> {
}
