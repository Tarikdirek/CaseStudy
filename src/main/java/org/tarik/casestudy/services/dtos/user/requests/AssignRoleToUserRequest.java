package org.tarik.casestudy.services.dtos.user.requests;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class AssignRoleToUserRequest {
    @NotNull
    @Positive(message = "Manager id must be positive")
    private int managerId;

    @NotNull
    @Positive(message = "User id must be positive")
    private int userId;

    @NotNull
    @Positive(message = "Role id must be positive")
    private int roleId;
}
