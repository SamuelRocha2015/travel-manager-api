package com.devs.travels.repository;

import com.devs.travels.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);
    boolean existsByEmailOrCpf(String email, String cpf);
    Optional<User> findByEmailAndActiveTrue(String email);
}

