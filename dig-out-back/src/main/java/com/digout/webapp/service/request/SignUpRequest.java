package com.digout.webapp.service.request;

public record SignUpRequest(
        String name,
        String email,
        String password) {
}
