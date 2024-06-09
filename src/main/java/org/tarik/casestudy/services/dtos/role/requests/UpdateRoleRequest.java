package org.tarik.casestudy.services.dtos.role.requests;

import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateRoleRequest {
    @Positive(message = "Id must be positive")
    private int id;
    @Size(min = 4, max = 10 , message = "Role name must be between 4 and 10 characters")
    private String name;
    private String description;

}
