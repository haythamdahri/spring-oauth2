package org.hightech.oauth.dao;

import org.hightech.oauth.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
@RestResource(path = "/users")
public interface UserRepository extends JpaRepository<User, String> {

    // Method to fetch user with a criteria
    public Collection<User> findByUsernameContainingIgnoreCaseOrFirstNameContainingIgnoreCaseOrLastNameContainingIgnoreCase(@Param(value = "username") String username, @Param(value = "firstName") String firstName, @Param(value = "lastName") String lastName);
}
