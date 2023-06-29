package com.SpringBoot_JWT.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "t_role")
@Getter
@Setter
public class Role extends BaseEntity {
    private String roleName;
    private String roleKey;

    @OneToMany(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    @JoinTable(name = "t_role_permission", joinColumns = { @JoinColumn(name = "role_id") }, inverseJoinColumns = {
            @JoinColumn(name = "permission_id") })
    private Set<Permission> permissions = new HashSet<>();

    public Role() {
    }

    public Role(long id) {
        super(id);
    }

    public Role(long id, String roleName, String roleKey, Set<Permission> permissions) {
        super(id);
        this.roleName = roleName;
        this.roleKey = roleKey;
        this.permissions = permissions;
    }

    public Role(String roleName, String roleKey, Set<Permission> permissions) {
        this.roleName = roleName;
        this.roleKey = roleKey;
        this.permissions = permissions;
    }
}
