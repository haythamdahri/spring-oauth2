package org.hightech.oauth.services;

import org.hightech.oauth.entities.Role;
import org.hightech.oauth.entities.RoleEnum;

import java.util.Collection;

public interface RoleService {

    public Role saveRole(Role role);

    public Role getRole(RoleEnum role);

    public Boolean deleteRole(RoleEnum roleEnum);

    public Collection<Role> getRoles();

}
