package org.tarik.casestudy.services.dtos.user.requests;

import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class AddUserRequest {
    @Positive(message = "Id must be positive")
    private int roleId;
    @Size(min = 4, max = 15 , message = "User name must be between 4 and 15 characters")
    private String username;
    @Size(min = 4, max = 15 , message = "Password must be between 4 and 15 characters")
    private String password;
}
