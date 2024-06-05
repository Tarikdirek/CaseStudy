package org.tarik.casestudy.services.abstracts;

import org.tarik.casestudy.services.dtos.authentication.requests.LoginRequest;

public interface AuthenticationService {
    void login(LoginRequest loginRequest);

}
