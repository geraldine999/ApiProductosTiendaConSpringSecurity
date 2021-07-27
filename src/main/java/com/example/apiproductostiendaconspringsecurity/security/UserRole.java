package com.example.apiproductostiendaconspringsecurity.security;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;

import static com.example.apiproductostiendaconspringsecurity.security.UserPermission.*;

public enum UserRole {
    CLIENTE(Set.of(USER_READ, PRODUCTO_READ)),
    ADMIN(Set.of(USER_READ, USER_WRITE, PRODUCTO_READ,PRODUCTO_WRITE));

    private final Set<UserPermission> userPermissions;

    UserRole(Set<UserPermission> userPermissions) {
        this.userPermissions = userPermissions;
    }

    public Set<UserPermission> getUserPermissions() {
        return userPermissions;
    }

    public Set<SimpleGrantedAuthority> getGrantedAuthorities(){
        Set<SimpleGrantedAuthority> permisos = getUserPermissions().stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                .collect(Collectors.toSet());

        permisos.add(new SimpleGrantedAuthority("ROLE_"+this.name()));
        return permisos;
    }

    public String getRole() {
        return this.name();
    }

}
