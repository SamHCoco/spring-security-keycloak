package com.samhcoco.project.spring.web.keycloak.security.model;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class KeycloakRole {
    private String id;
    private String name;
    private boolean composite;
    private boolean clientRole;
    private String containerId;
}
