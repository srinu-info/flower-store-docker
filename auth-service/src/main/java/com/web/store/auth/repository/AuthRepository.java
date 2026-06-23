package com.web.store.auth.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.web.store.auth.entity.User;
@Repository
public interface AuthRepository extends JpaRepository<User, Long> {
Optional<User> findByEmail(String email);
}
