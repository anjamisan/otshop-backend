package com.example.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import model.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    
    // spring ovo sam zna da iskoristi, nema potrebe za implementacijom
    Optional<User> findByUsername(String username);

    //preko emaila
    Optional<User> findByEmail(String email);

    //preko ida
    Optional<User> findById(int id);
    
    //oba
    @Query("SELECT u FROM User u WHERE u.username = ?1 OR u.email = ?1")
    Optional<User> findByUsernameOrEmail(String identifier);
}
