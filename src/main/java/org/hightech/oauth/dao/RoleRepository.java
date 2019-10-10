package org.hightech.oauth.dao;

import org.hightech.oauth.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.stereotype.Repository;

@Repository
@RestResource(path = "/roles")
public interface RoleRepository extends JpaRepository<Role, String> {
}
