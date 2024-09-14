package com.saji.dashboard_backend.modules.user_managment.entities;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

import com.saji.dashboard_backend.shared.entites.BaseEntity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "roles")
public class Role extends BaseEntity {
    @Column(nullable = false, length = 25)
    private String role;

    @Column(columnDefinition = "INT")
    private boolean enabled = true;

    @ManyToMany(mappedBy = "roles")
    private List<User> users = new ArrayList<>();

    @OneToMany(mappedBy = "role", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<Permission> permissions = new ArrayList<>();

    public List<SimpleGrantedAuthority> getGrantedAuthorities() {
        List<SimpleGrantedAuthority> grantedAuthorities = new ArrayList<>();
        for (Permission p : permissions) {
            if (p.isCreateR()) {
                grantedAuthorities.add(new SimpleGrantedAuthority("CREATE_" + p.getEntity().toUpperCase()));
            }
            if (p.isReadR()) {
                grantedAuthorities.add(new SimpleGrantedAuthority("READ_" + p.getEntity().toUpperCase()));
            }
            if (p.isEditR()) {
                grantedAuthorities.add(new SimpleGrantedAuthority("EDIT_" + p.getEntity().toUpperCase()));
            }
            if (p.isDeleteR()) {
                grantedAuthorities.add(new SimpleGrantedAuthority("DELETE_" + p.getEntity().toUpperCase()));
            }
        }
        return grantedAuthorities;
    }
}
