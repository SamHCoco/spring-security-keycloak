package com.samhcoco.project.spring.web.keycloak.security.configuration;

import lombok.val;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public class KeycloakRoleConverter implements Converter<Jwt, Collection<GrantedAuthority>> {

    @Override
    public Collection<GrantedAuthority> convert(Jwt jwt) {
        val roles = new ArrayList<GrantedAuthority>();
        val realmAccess = (Map<String, Object>) jwt.getClaims().get("realm_access");

        ((List<String>) realmAccess.get("roles")).stream()
                                   .map(role -> "ROLE_" + role)
                                   .forEach(r -> roles.add(new SimpleGrantedAuthority(r)));
        return roles;
    }
}
