package com.acme.ideogo.repository;

import com.acme.ideogo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
