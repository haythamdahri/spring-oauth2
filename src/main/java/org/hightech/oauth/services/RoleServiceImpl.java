package org.hightech.oauth.services;

import org.hightech.oauth.dao.RoleRepository;
import org.hightech.oauth.entities.Role;
import org.hightech.oauth.entities.RoleEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public Role saveRole(Role role) {
        return this.roleRepository.save(role);
    }

    @Override
    public Role getRole(RoleEnum role) {
        return roleRepository.findById(role.name()).orElse(null);
    }

    @Override
    public Boolean deleteRole(RoleEnum roleEnum) {
        this.roleRepository.deleteById(roleEnum.name());
        return true;
    }

    @Override
    public Collection<Role> getRoles() {
        return this.roleRepository.findAll();
    }
}
