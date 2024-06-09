package org.tarik.casestudy.services.dtos.authentication.requests;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegisterRequest {

    @NotNull
    @Size(min = 3, max = 15, message = "Username must be between 3 and 15 characters long")
    private String username;
    @NotNull
    @Size(min = 3, max = 15, message = "Password must be between 3 and 15 characters long")
    private String password;
    @NotNull
    @Positive(message = "Role id must be positive")
    private int roleId;
}
