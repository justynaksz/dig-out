package com.digout.webapp.service.request;

public record SignInRequest(
        String nickname,
        String password) {
}
