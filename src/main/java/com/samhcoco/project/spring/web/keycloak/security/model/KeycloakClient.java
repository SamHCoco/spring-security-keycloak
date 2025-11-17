package com.samhcoco.project.spring.web.keycloak.security.model;

import lombok.*;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class KeycloakClient {
    private String id;
    private String clientId;
    private String name;
    private boolean directAccessGrantsEnabled;
    private boolean implicitFlowEnabled;
    private List<String> redirectUris;
    private boolean publicClient;
}
