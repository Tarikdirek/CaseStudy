package org.tarik.casestudy.services.dtos.role.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class GetAllRolesResponse {

    private int id;
    private String name;
    private String description;
}
