package com.project.sokoni.repositories;

import com.project.sokoni.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
