package org.tarik.casestudy.services.dtos.user.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class GetAllUsersResponse {
    private int id;
    private String username;
    private String password;
    private String roleId;
    private String roleName;
}

