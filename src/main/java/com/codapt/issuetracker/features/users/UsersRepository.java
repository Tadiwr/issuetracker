package com.codapt.issuetracker.features.users;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersRepository extends CrudRepository<User, Long> {
    Optional<User> findByEmailAndHashedPassword(String email, String hashedPassword);
}
