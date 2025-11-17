package com.samhcoco.project.spring.web.keycloak.security.model;

import lombok.*;

import java.util.List;
import java.util.Map;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class KeycloakUser {
    private String id;
    private String username;
    private String firstName;
    private String lastName;
    private String email;
    private List<String> realmRoles;
    private Map<String, List<String>> clientRoles;
    private Boolean enabled;
    private Boolean emailVerified;
    private Long createdTimestamp;
    private Map<String, Object> attributes;
    private List<Credential> credentials;
}
