package com.itechart.identity_service.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "users")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "email", unique = true)
    @NotNull(message = "Email is required")
    @NotBlank(message = "Email can't be empty")
    @Size(min = 2, max = 100, message = "Name string length limits exceeded")
    private String email;

    @Column(name = "password")
    @NotNull(message = "Password is required")
    @NotBlank(message = "Password can't be empty")
    @Size(min = 6, max = 200, message = "Password string length limits exceeded")
    private String password;

    @Column(name = "first_name")
    @NotNull(message = "First name is required")
    @NotBlank(message = "First name can't be empty")
    @Size(min = 2, max = 100, message = "First name string length limits exceeded")
    private String firstName;

    @Column(name = "last_name")
    @NotNull(message = "Last name is required")
    @NotBlank(message = "Last name can't be empty")
    @Size(min = 2, max = 100, message = "Last name string length limits exceeded")
    private String lastName;

    @Column(name = "role")
    @NotNull(message = "Role is required")
    @Enumerated(EnumType.STRING)
    private Role role;

    private Boolean accountNonExpired;

    private Boolean accountNonLocked;

    private Boolean credentialsNonExpired;

    private Boolean enabled;

    @Override
    public List<GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(role.toString()));
        return authorities;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return accountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return accountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return credentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }
}
