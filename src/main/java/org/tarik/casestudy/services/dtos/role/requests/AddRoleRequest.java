package org.tarik.casestudy.services.dtos.role.requests;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class AddRoleRequest {
    @NotNull
    @Size(min = 2, max = 50 , message = "Role name must be between 4 and 50 characters")
    private String name;

    private String description;

}
