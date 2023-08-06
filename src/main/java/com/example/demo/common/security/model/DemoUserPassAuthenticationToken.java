package com.example.demo.common.security.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

@Getter @Setter
public class DemoUserPassAuthenticationToken extends UsernamePasswordAuthenticationToken {

    private Long originalId;

    public DemoUserPassAuthenticationToken(Long originalId, Object principal, Object credentials, Collection<? extends GrantedAuthority> authorities) {
        super(principal, credentials, authorities);
        this.originalId = originalId;
    }

    public DemoUserPassAuthenticationToken(Object principal, Object credentials) {
        super(principal, credentials);
    }
}
