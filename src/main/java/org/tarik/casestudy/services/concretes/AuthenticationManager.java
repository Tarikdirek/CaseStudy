package org.tarik.casestudy.services.concretes;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.tarik.casestudy.services.abstracts.AuthenticationService;
import org.tarik.casestudy.services.abstracts.UserService;
import org.tarik.casestudy.services.constants.Messages;
import org.tarik.casestudy.services.dtos.authentication.requests.LoginRequest;

@Service
@AllArgsConstructor
public class AuthenticationManager implements AuthenticationService {

    private UserService userService;
    @Override
    public void login(LoginRequest loginRequest) {
        checkIfUserNotExists(loginRequest.getUsername());
        checkIfPasswordIsCorrect(loginRequest.getUsername(), loginRequest.getPassword());
    }

    private void checkIfPasswordIsCorrect(String username, String password) {
        var user = userService.getByName(username);
        if (!user.getPassword().equals(password)) {
            throw new RuntimeException(Messages.INCORRECT_PASSWORD);
        }
    }

    private void checkIfUserNotExists(String username) {
        var user = userService.getByName(username);
        if (user == null) {
            throw new RuntimeException(Messages.USER_NOT_FOUND);
        }
    }
}
