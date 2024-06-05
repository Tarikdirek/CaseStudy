package org.tarik.casestudy.controllers;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.tarik.casestudy.services.abstracts.AuthenticationService;
import org.tarik.casestudy.services.dtos.authentication.requests.LoginRequest;

@RestController
@RequestMapping("api/v1/auth")
@AllArgsConstructor
@CrossOrigin
public class AuthenticationController {
    private final AuthenticationService authenticationService;

    @PostMapping("/login")
    public void login(LoginRequest loginRequest) {
        authenticationService.login(loginRequest);
    }
}
