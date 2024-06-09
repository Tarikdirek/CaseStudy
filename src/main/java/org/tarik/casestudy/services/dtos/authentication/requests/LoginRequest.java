package org.tarik.casestudy.services.dtos.authentication.requests;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginRequest {
    @NotNull
    @Size(min = 3, max = 15, message = "Username must be between 3 and 15 characters long")
    private String username;
    @NotNull
    @Size(min = 3, max = 15, message = "Password must be between 3 and 15 characters long")
    private String password;
}
