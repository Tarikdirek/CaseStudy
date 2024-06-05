package org.tarik.casestudy.services.dtos.user.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class AssignRoleToUserRequest {
    private int managerId;
    private int userId;
    private int roleId;
}
