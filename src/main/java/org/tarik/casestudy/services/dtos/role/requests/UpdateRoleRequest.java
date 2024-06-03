package org.tarik.casestudy.services.dtos.role.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateRoleRequest {

    private int id;
    private String name;
    private String description;
}
