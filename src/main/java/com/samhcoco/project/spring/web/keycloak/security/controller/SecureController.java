package com.samhcoco.project.spring.web.keycloak.security.controller;

import com.samhcoco.project.spring.web.keycloak.security.enums.KeycloakRoles;
import com.samhcoco.project.spring.web.keycloak.security.model.KeycloakUser;
import com.samhcoco.project.spring.web.keycloak.security.service.KeycloakService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

import static java.util.Objects.isNull;
import static org.springframework.http.HttpStatus.*;

@Slf4j
@RestController
@RequestMapping("api/v1/secure")
@RequiredArgsConstructor
public class SecureController {

    private final KeycloakService keycloakService;

    @PostMapping("keycloak/user")
    public ResponseEntity<Object> createKeycloakUser(@RequestBody KeycloakUser keycloakUser) {
        val createdUser = keycloakService.create(keycloakUser);

        if (isNull(createdUser)) {
            val error = "Failed to create user " + keycloakUser.getEmail() + ": could not register the user to Keycloak.";
            log.error(error);
            return ResponseEntity.status(INTERNAL_SERVER_ERROR)
                                 .body(error);
        }

        val roles = keycloakService.assignRoles(keycloakUser.getId(), Set.of(KeycloakRoles.USER.name().toLowerCase()));

        if (isNull(roles) || !roles.getStatusCode().is2xxSuccessful()) {
            val error = "Failed to assign user " + createdUser.getEmail()  + " Keycloak role: 'user'";
            log.error(error);
            keycloakService.delete(keycloakUser.getId());
            return ResponseEntity.status(INTERNAL_SERVER_ERROR)
                                 .body(error);
        }

        return ResponseEntity.status(CREATED)
                             .body(createdUser);
    }

    @GetMapping("public")
    public ResponseEntity<Object> publicResource() {
        val auth = SecurityContextHolder.getContext().getAuthentication();
        return new ResponseEntity<>("<h1>Public</h1>", OK);
    }

    @GetMapping("admin")
    @PreAuthorize("hasRole('admin')")
    public ResponseEntity<Object> admin() {
        return new ResponseEntity<>("<h1>admin</h1>", OK);
    }

    @GetMapping("user")
    @PreAuthorize("hasRole('user')")
    public ResponseEntity<Object> user() {
        return new ResponseEntity<>("<h1>user</h1>", OK);
    }

    @GetMapping("admin-user")
    @PreAuthorize("hasAnyRole('user', 'admin')")
    public ResponseEntity<Object> adminUser() {
        return new ResponseEntity<>("<h1>admin & user</h1>", OK);
    }

}
