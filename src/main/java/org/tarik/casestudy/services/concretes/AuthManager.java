package org.tarik.casestudy.services.concretes;

import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.tarik.casestudy.core.services.JwtService;
import org.tarik.casestudy.core.utilies.mappers.ModelMapperService;
import org.tarik.casestudy.entities.concretes.Role;
import org.tarik.casestudy.entities.concretes.User;
import org.tarik.casestudy.services.abstracts.AuthService;
import org.tarik.casestudy.services.abstracts.RoleService;
import org.tarik.casestudy.services.abstracts.UserService;
import org.tarik.casestudy.services.constants.Messages;
import org.tarik.casestudy.services.dtos.authentication.requests.LoginRequest;
import org.tarik.casestudy.services.dtos.authentication.requests.RegisterRequest;
import org.tarik.casestudy.services.dtos.authentication.responses.AuthenticationResponse;
import org.tarik.casestudy.services.dtos.user.requests.AddUserRequest;

@Service
@AllArgsConstructor
public class AuthManager implements AuthService {

    private final UserService userService;
    private  final RoleService roleService;
    private final PasswordEncoder passwordEncoder;

    private final JwtService jwtService;
    private final ModelMapperService modelMapperService;

    private final AuthenticationManager authenticationManager;
    @Override
    public AuthenticationResponse login(LoginRequest loginRequest) {

        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
        var user = userService.getByName(loginRequest.getUsername());
        var mappedUser = modelMapperService.forResponse().map(user, User.class);
        var jwtToken = jwtService.generateToken(mappedUser);
        var refreshToken = jwtService.generateRefreshToken(mappedUser);
        return AuthenticationResponse.builder()
                .accessToken(jwtToken)
                .refreshToken(refreshToken)
                .id(user.getId())
                .build();

    }

    @Override
    public AuthenticationResponse register(RegisterRequest registerRequest) {
        var role = findRole(registerRequest.getRoleId());
        var user = User.builder()
                .username(registerRequest.getUsername())
                .password(passwordEncoder.encode(registerRequest.getPassword()))
                .role(role)
                .build();
        var userDto = modelMapperService.forRequest().map(user, AddUserRequest.class);
        userService.add(userDto);
        var jwtToken = jwtService.generateToken(user);
        var refreshToken = jwtService.generateRefreshToken(user);
        return AuthenticationResponse.builder()
                .accessToken(jwtToken)
                .user(user)
                .refreshToken(refreshToken)
                .build();
    }


    private Role findRole(int roleId){
        var roleDto = roleService.getById(roleId);
        var role = modelMapperService.forRequest().map(roleDto, Role.class);
        return role;

    }
}
