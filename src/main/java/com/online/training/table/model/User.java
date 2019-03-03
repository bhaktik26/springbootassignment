package com.online.training.table.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "users")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class User {

    @Id
    @Column(name = "id")
    @SequenceGenerator(name="my_seq", sequenceName="users_id_seq")
    @GeneratedValue(strategy=GenerationType.AUTO,generator = "my_seq")
    private Long id;

    @Column(name = "username")
    @NotNull
    @Size(min = 1, max = 100)
    private String username;

    @Column(name = "password")
    @NotNull
    @Size(min = 1, max = 100)
    private String password;

    @Column(name = "role")
    @NotNull
    @Size(min = 1, max = 100)
    private String role;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", role='" + role + '\'' +
                '}';
    }
}
