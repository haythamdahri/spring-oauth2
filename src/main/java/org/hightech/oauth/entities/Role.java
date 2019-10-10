package org.hightech.oauth.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;

/*
* Role entity
*/
@Entity
@Table(name = "role")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Role implements Serializable {

    /*
    * Role identifier
    */
    @Id
    @Enumerated(EnumType.STRING)
    private RoleEnum roleName;

    /*
    * Many To Many relationship
    */
    @ManyToMany(mappedBy = "roles")
    private Collection<User> users;

}
