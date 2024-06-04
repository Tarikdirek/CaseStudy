package org.tarik.casestudy.services.dtos.user.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class UpdateUserRequest {

    private int id;
    private int roleId;
    private String username;
    private String password;
}
