package org.tarik.casestudy.services.dtos.user.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class UpdateUserRequest {

    private int id;
    private String username;
    private String password;
    private String roleId;
}
