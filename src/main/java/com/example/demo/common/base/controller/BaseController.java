package com.example.demo.common.base.controller;

import com.example.demo.common.security.model.DemoUserPassAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Objects;

public class BaseController {

    protected DemoUserPassAuthenticationToken getAuthentication() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth instanceof UsernamePasswordAuthenticationToken) {
            return (DemoUserPassAuthenticationToken) auth;
        }
        return null;
    }

    protected String getPrincipal() {
        DemoUserPassAuthenticationToken authentication = getAuthentication();
        if (Objects.nonNull(authentication)) {
            return authentication.getPrincipal().toString();
        }
        return null;
    }

    protected Long getOriginalId() {
        DemoUserPassAuthenticationToken authentication = getAuthentication();

        if (Objects.nonNull(authentication)) {
            return authentication.getOriginalId();
        }

        return null;
    }

}