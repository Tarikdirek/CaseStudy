package org.tarik.casestudy.services.abstracts;

import org.tarik.casestudy.services.dtos.role.requests.AddRoleRequest;
import org.tarik.casestudy.services.dtos.role.requests.DeleteRoleRequest;
import org.tarik.casestudy.services.dtos.role.requests.UpdateRoleRequest;
import org.tarik.casestudy.services.dtos.role.responses.GetAllRolesResponse;
import org.tarik.casestudy.services.dtos.role.responses.GetRoleByIdResponse;

import java.util.List;

public interface RoleService {
    void add(AddRoleRequest addRoleRequest);
    void update(UpdateRoleRequest updateRoleRequest);
    void delete(DeleteRoleRequest deleteRoleRequest);
    void assignRoleToUser(int userId,int assignedUser, int roleId);
    void removeRoleFromUser(int userId, int roleId,int assignUser);

    List<GetAllRolesResponse> getAll();
    GetRoleByIdResponse getById(int id);
}
