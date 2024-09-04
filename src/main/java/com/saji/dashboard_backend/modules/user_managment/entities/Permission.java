package com.saji.dashboard_backend.modules.user_managment.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "permissions")
public class Permission {
    @Id
    private Long id;

    @Column(nullable = false)
    private String entity;
    
    @Column(name = "create", columnDefinition = "INT")
    private boolean create;

    @Column(name = "read", columnDefinition = "INT")
    private boolean read;

    @Column(name = "edit", columnDefinition = "INT")
    private boolean edit;

    @Column(name = "delete", columnDefinition = "INT")
    private boolean delete;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;
}
