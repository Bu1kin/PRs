package com.example.demo.Models;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    USER, ADMIN, MACHINE;

    @Override
    public String getAuthority(){
        return name();
    }
}
