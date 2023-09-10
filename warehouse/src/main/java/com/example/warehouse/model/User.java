package com.example.warehouse.model;

import javax.persistence.*;
import java.time.LocalDateTime;

import static java.util.Objects.nonNull;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String password;
    @Column(nullable = false, columnDefinition = "TINYINT(1)")
    private boolean confirmed;
    @Column(nullable = false, columnDefinition = "TINYINT(1)")
    private boolean blocked;
    private String token;
    private LocalDateTime tokenExperience;
    @OneToOne
    private Role role;

    public User() {

    }

    public String getUsername() {
        return username;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean isConfirmed() {
        return confirmed;
    }

    public void setConfirmed(boolean confirmed) {
        this.confirmed = confirmed;
    }

    public boolean isBlocked() {
        return blocked;
    }

    public void setBlocked(boolean blocked) {
        this.blocked = blocked;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public LocalDateTime getTokenExperience() {
        return tokenExperience;
    }

    public void setTokenExperience(LocalDateTime tokenExperience) {
        this.tokenExperience = tokenExperience;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }


    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public User(Long id, String username, Role role) {
        this.id = id;

        if (nonNull(username)) {
            this.username = username;
        }
        if (nonNull(role)) {
            this.role = role;
        }
    }

}