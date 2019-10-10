package org.hightech.oauth.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.util.Collection;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User implements Serializable {


    @Id
    @Column(name = "username", unique = true)
    @NotNull(message = "username cannot be null")
    @NotEmpty(message = "username must be filled")
    @Pattern(regexp = "^[a-zA-Z0-9]{2,100}$", message = "Invalid username")
    private String username;

    @Column(name = "firstname")
    @NotNull(message = "First name cannot be null")
    @NotEmpty(message = "First name must be filled")
    @Pattern(regexp = "^[a-zA-Z0-9]{2,100}$", message = "Invalid user firstname")
    private String firstName;

    @Column(name = "lastname")
    @NotNull(message = "Last name cannot be null")
    @NotEmpty(message = "Last name must be filled")
    @Pattern(regexp = "^[a-zA-Z0-9]{2,100}$", message = "Invalid user lastname")
    private String lastName;

    @Column(name = "password")
    private String password;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name="users_roles", joinColumns = @JoinColumn(name = "user_name"), inverseJoinColumns = @JoinColumn(name = "role_name"))
    private Collection<Role> roles;
}
