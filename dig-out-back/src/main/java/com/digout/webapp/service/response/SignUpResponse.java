package com.digout.webapp.service.response;

import com.digout.webapp.service.DTO.AppUserDTO;

public class SignUpResponse {

    private int statusCode;
    private String error;
    private String message;
    private String token;
    private String refreshToken;
    private String expirationTime;
    private AppUserDTO appUserDTO;

    public SignUpResponse(int statusCode, String error, String message, String token, String refreshToken,
                          String expirationTime, AppUserDTO appUserDTO) {
        this.statusCode = statusCode;
        this.error = error;
        this.message = message;
        this.token = token;
        this.refreshToken = refreshToken;
        this.expirationTime = expirationTime;
        this.appUserDTO = appUserDTO;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public String getExpirationTime() {
        return expirationTime;
    }

    public void setExpirationTime(String expirationTime) {
        this.expirationTime = expirationTime;
    }

    public AppUserDTO getAppUserDTO() {
        return appUserDTO;
    }

    public void setAppUserDTO(AppUserDTO appUserDTO) {
        this.appUserDTO = appUserDTO;
    }
}
