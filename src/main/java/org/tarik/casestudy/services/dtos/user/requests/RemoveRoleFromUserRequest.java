package org.tarik.casestudy.services.dtos.user.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RemoveRoleFromUserRequest {

    private int managerId;
    private int userId;
}
