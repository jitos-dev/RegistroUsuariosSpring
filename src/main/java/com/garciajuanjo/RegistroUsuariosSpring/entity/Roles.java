package com.garciajuanjo.RegistroUsuariosSpring.entity;


import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "roles")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Roles {

    @Id
    @GeneratedValue
    @Column(name = "role_id", unique = true, nullable = false)
    private int roleId;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "enabled", nullable = false)
    private boolean enabled;

    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "roles")
    private Set<UserApp> userApps = new HashSet<>();

    public Roles(String name, boolean enabled) {
        this.name = name;
        this.enabled = enabled;
    }

    public Roles(int roleId, String name) {
        this.roleId = roleId;
        this.name = name;
        this.enabled = true;
    }
}
