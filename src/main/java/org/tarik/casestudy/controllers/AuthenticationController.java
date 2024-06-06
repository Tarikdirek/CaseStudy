package org.tarik.casestudy.controllers;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.tarik.casestudy.services.abstracts.AuthService;
import org.tarik.casestudy.services.dtos.authentication.requests.LoginRequest;
import org.tarik.casestudy.services.dtos.authentication.requests.RegisterRequest;
import org.tarik.casestudy.services.dtos.authentication.responses.AuthenticationResponse;

@RestController
@RequestMapping("api/v1/auth")
@AllArgsConstructor
@CrossOrigin
public class AuthenticationController {
    private final AuthService authenticationService;

    @PostMapping("/login")
    public AuthenticationResponse login(@RequestBody LoginRequest loginRequest) {
       return authenticationService.login(loginRequest);
    }

    @PostMapping("/register")
    public AuthenticationResponse register(@RequestBody RegisterRequest registerRequest) {
       return authenticationService.register(registerRequest);
    }
}
