package org.hightech.oauth.services;

import org.hightech.oauth.entities.User;

import java.util.Collection;

/*
 * User Service
 */
public interface UserService {

    public User saveUser(User user);

    public User getUser(String username);

    public Boolean deleteUser(String username);

    public Collection<User> getUsers();

    public Collection<User> getUsers(String search);

}
