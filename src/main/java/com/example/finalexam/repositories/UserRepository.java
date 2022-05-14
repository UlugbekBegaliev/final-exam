package com.example.finalexam.repositories;

import com.example.finalexam.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    User findUserById(Integer id);

    boolean existsByEmail(String email);
    User findUserByEmail(String email);

    Optional<User> findByEmail(String email);
}
