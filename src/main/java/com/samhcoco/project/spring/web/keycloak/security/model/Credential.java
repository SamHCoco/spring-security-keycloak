package com.samhcoco.project.spring.web.keycloak.security.model;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class Credential {
    private boolean temporary;
    private String value;
}
