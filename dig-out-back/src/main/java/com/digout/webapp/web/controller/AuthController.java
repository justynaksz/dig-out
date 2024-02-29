package com.digout.webapp.web.controller;

import com.digout.webapp.service.DTO.AppUserDTO;
import com.digout.webapp.service.exeption.DuplicateException;
import com.digout.webapp.service.exeption.EmptyFieldException;
import com.digout.webapp.service.exeption.InvalidInputException;
import com.digout.webapp.service.exeption.NotFoundException;
import com.digout.webapp.service.request.SignInRequest;
import com.digout.webapp.service.request.SignUpRequest;
import com.digout.webapp.service.response.RefreshResponse;
import com.digout.webapp.service.response.SignInResponse;
import com.digout.webapp.service.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class AuthController {

    private final AuthService authService;

    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/signup")
    public ResponseEntity<AppUserDTO> signUp(@RequestBody SignUpRequest signUpRequest) throws InvalidInputException,
            DuplicateException, EmptyFieldException {
        return ResponseEntity.ok(authService.signUp(signUpRequest));
    }

    @PostMapping("/signin")
    public ResponseEntity<SignInResponse> signIn(@RequestBody SignInRequest signInRequest) {
        return ResponseEntity.ok(authService.signIn(signInRequest));
    }

    @PostMapping("/refresh")
    public ResponseEntity<RefreshResponse> refreshToken(@RequestBody String token) throws NotFoundException {
        return ResponseEntity.ok(authService.refreshToken(token));
    }
}
