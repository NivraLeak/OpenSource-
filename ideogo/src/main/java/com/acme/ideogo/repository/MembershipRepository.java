package com.acme.ideogo.repository;

import com.acme.ideogo.model.Membership;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MembershipRepository extends JpaRepository<Membership, Long> {

}
