package org.tarik.casestudy.services.abstracts;

import org.tarik.casestudy.services.dtos.authentication.requests.LoginRequest;
import org.tarik.casestudy.services.dtos.authentication.requests.RegisterRequest;
import org.tarik.casestudy.services.dtos.authentication.responses.AuthenticationResponse;

public interface AuthService {
    AuthenticationResponse login(LoginRequest loginRequest);
    AuthenticationResponse register(RegisterRequest registerRequest);


}
