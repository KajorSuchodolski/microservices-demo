package com.example.microservicesdemo.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.UUID;

@Entity
@Table(
        name = "account",
        uniqueConstraints = {@UniqueConstraint(columnNames = "login", name = Account.LOGIN_CONSTRAINT_NAME)},
        indexes = {@Index(columnList = "id", name = "id_index"), @Index(columnList = "login", name = "login_index"),})
@NoArgsConstructor
@ToString
public class Account {

    public static final String LOGIN_CONSTRAINT_NAME = "login_unique_constraint";

    @Id
    @Column(name = "id", nullable = false, updatable = false)
    @Getter
    @Setter
    private UUID id;

    @Version
    @Column(name = "version", nullable = false)
    @Basic(optional = false)
    @Getter
    @Setter
    private Long version;

    @Column(name = "login", nullable = false)
    @Basic(optional = false)
    @Getter
    @Setter
    private String login;

    @Column(name = "password", nullable = false)
    @Basic(optional = false)
    @Getter
    @Setter
    private String password;

    @Column(name = "first_name", nullable = false)
    @Basic(optional = false)
    @Getter
    @Setter
    private String firstName;

    @Column(name = "last_name", nullable = false)
    @Basic(optional = false)
    @Getter
    @Setter
    private String lastName;

    @Column(name = "roles", nullable = true)
    @Basic(optional = true)
    @Getter
    @Setter
    private String roles;
}
