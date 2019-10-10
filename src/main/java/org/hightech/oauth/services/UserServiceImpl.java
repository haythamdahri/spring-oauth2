package org.hightech.oauth.services;

import org.hightech.oauth.dao.UserRepository;
import org.hightech.oauth.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collection;

/*
 * User Service implementor
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public User saveUser(User user) {
        // Encrypt user password
        user.setPassword(this.bCryptPasswordEncoder.encode(user.getPassword()));
        // Save user
        return this.userRepository.save(user);
    }

    @Override
    public User getUser(String username) {
        return this.userRepository.findById(username).orElse(null);
    }

    @Override
    public Boolean deleteUser(String username) {
        this.userRepository.deleteById(username);
        return true;
    }

    @Override
    public Collection<User> getUsers() {
        return this.userRepository.findAll();
    }

    @Override
    public Collection<User> getUsers(String search) {
        return this.userRepository.findByUsernameContainingIgnoreCaseOrFirstNameContainingIgnoreCaseOrLastNameContainingIgnoreCase(search, search, search);
    }
}
