package com.connexus.entities;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity(name = "user")
@Table(name = "users")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString(exclude = "contacts")
public class User implements UserDetails {

    // Basic Information
    @Id
    private String userId;

    @Column(name = "user_name", nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String email;

    @Getter(AccessLevel.NONE) // Hides the field from the generated getters
    private String password;

    @Column(length = 1000)
    private String about;

    @Column(length = 2048)
    private String profilePic;

    private String phoneNumber;

    // Information
    @Getter(AccessLevel.NONE) // Hides the field from the generated getters
    private boolean enabled = true;

    private boolean emailVerified = false;
    private boolean phoneVerified = false;

    // Self, Google, Facebook, Twitter, etc.
    @Enumerated(value = EnumType.STRING)
    private Providers provider = Providers.SELF;
    private String providerUserId;

    // Additional Information if needed
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<Contact> contacts = new ArrayList<>();

    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> roleList = new ArrayList<>();

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<SimpleGrantedAuthority> roles = roleList.stream().map(role -> new SimpleGrantedAuthority(role))
                .collect(Collectors.toList());

        return roles;
    }

    @Override
    public String getUsername() {
        return this.email; // Username is typically the email
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public String getPassword() {
        return this.password;
    }
}