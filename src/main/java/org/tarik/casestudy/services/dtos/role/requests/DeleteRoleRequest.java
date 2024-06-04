package org.tarik.casestudy.services.dtos.role.requests;

import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class DeleteRoleRequest {
    @Positive(message = "Id must be positive")
    private int id;

}
