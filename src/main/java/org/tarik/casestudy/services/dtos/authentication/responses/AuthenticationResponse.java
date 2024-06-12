package org.tarik.casestudy.services.dtos.authentication.responses;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.tarik.casestudy.entities.concretes.User;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AuthenticationResponse {
    private int id;
    private String accessToken;
    private String refreshToken;
    private User user;

}
