package Esercizio.S7L2.repositories;

import java.util.Optional;
import java.util.UUID;

import Esercizio.S7L2.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UsersDAO extends JpaRepository<User, UUID> {
    boolean existsByEmail(String email);
    Optional<User> findByEmail(String email);

}