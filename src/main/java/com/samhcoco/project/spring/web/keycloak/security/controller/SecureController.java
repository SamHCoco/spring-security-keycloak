package com.samhcoco.project.spring.web.keycloak.security.controller;

import lombok.val;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("api/v1/secure")
public class SecureController {

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
