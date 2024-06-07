package com.digout.webapp.web.controller;

import com.digout.webapp.service.DTO.AppUserDTO;
import com.digout.webapp.service.exeption.InvalidInputException;
import com.digout.webapp.service.exeption.NotFoundException;
import com.digout.webapp.service.service.AppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AppUserController {

    private final AppUserService appUserService;


    @Autowired
    public AppUserController(AppUserService appUserService) {
        this.appUserService = appUserService;
    }

    @GetMapping("/profile/{id}")
    public ResponseEntity<AppUserDTO> getUserByToken(@PathVariable String token) throws NotFoundException {
        return ResponseEntity.ok(appUserService.getUserByToken(token));
    }
}
