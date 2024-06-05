package org.tarik.casestudy.services.dtos.user.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class GetUserByNameResponse {
    private int id;
    private int roleId;
    private String username;
    private String password;
    private String roleName;
}
