package org.tarik.casestudy.services.concretes;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;
import org.tarik.casestudy.core.utilies.mappers.ModelMapperService;
import org.tarik.casestudy.entities.concretes.Role;
import org.tarik.casestudy.entities.concretes.User;
import org.tarik.casestudy.repositories.RoleRepository;
import org.tarik.casestudy.services.abstracts.RoleService;
import org.tarik.casestudy.services.abstracts.UserService;
import org.tarik.casestudy.services.dtos.role.requests.AddRoleRequest;
import org.tarik.casestudy.services.dtos.role.requests.DeleteRoleRequest;
import org.tarik.casestudy.services.dtos.role.requests.UpdateRoleRequest;
import org.tarik.casestudy.services.dtos.role.responses.GetAllRolesResponse;
import org.tarik.casestudy.services.dtos.role.responses.GetRoleByIdResponse;
import org.tarik.casestudy.services.dtos.user.requests.UpdateUserRequest;
import org.tarik.casestudy.services.dtos.user.responses.GetUserByIdResponse;

import java.util.List;
@AllArgsConstructor
@Service
public class RoleManager implements RoleService {
    private final ModelMapperService modelMapperService;
    private final RoleRepository roleRepository;
    private final UserService userService;
    @Override
    public void add(AddRoleRequest addRoleRequest) {
        checkIfRoleExists(addRoleRequest.getName());
        Role role = modelMapperService.forRequest().map(addRoleRequest, Role.class);
        roleRepository.save(role);

    }



    @Override
    public void update(UpdateRoleRequest updateRoleRequest) {
        Role role = roleRepository.findById(updateRoleRequest.getId())
                .orElseThrow(() -> new RuntimeException("Role not found"));
        role.setName(updateRoleRequest.getName());
        role.setDescription(updateRoleRequest.getDescription());
        roleRepository.save(role);
    }

    @Override
    public void delete(DeleteRoleRequest deleteRoleRequest) {
        var role = roleRepository.findById(deleteRoleRequest.getId());
        role.ifPresent(roleRepository::delete);
    }

    @Override
    //parametreleri bir sınıf içerisinde topla
    public void assignRoleToUser(int userId,int assignUser, int roleId) {
            checkIsUserAManager(userId);
            GetUserByIdResponse assignedUser = userService.getById(assignUser);
            User mappedAssignedUser = modelMapperService.forResponse().map(assignedUser, User.class);
            UpdateUserRequest updateRequest = modelMapperService.forRequest().map(mappedAssignedUser, UpdateUserRequest.class);
            Role role = roleRepository.findById(roleId).orElseThrow();

            mappedAssignedUser.setRole(role);
            userService.update(updateRequest);

    }

    @Override
    //parametreleri bir sınıf içerisinde topla
    public void removeRoleFromUser(int userId, int roleId,int assignUser) {
        checkIsUserAManager(userId);
        GetUserByIdResponse assignedUser = userService.getById(assignUser);
        User mappedAssignedUser = modelMapperService.forResponse().map(assignedUser, User.class);
        UpdateUserRequest updateRequest = modelMapperService.forRequest().map(mappedAssignedUser, UpdateUserRequest.class);

        mappedAssignedUser.setRole(null);
        userService.update(updateRequest);
    }

    @Override
    public List<GetAllRolesResponse> getAll() {
        var roles = roleRepository.findAll();
        var rolesList = roles
                .stream()
                .map(role -> modelMapperService
                        .forResponse()
                        .map(role, GetAllRolesResponse.class))
                .toList();
        return rolesList;
    }

    @Override
    public GetRoleByIdResponse getById(int id) {
        var role = roleRepository.findById(id);
        var mappedRole = modelMapperService.forResponse().map(role, GetRoleByIdResponse.class);
        return mappedRole;
    }

    private void checkIfRoleExists(String roleName) {
        var roles = getAll();
        if (roles.stream().anyMatch(role -> role.getName().equals(roleName))) {
            throw new RuntimeException("Role already exists");
        }
    }
    private void checkIsUserAManager(int user) {
        var userResponse = userService.getById(user);
        var mappedUser = modelMapperService.forResponse().map(userResponse, User.class);
        if (!mappedUser.getRole().getName().equals("Manager")) {
            throw  new RuntimeException("User is not a manager");
        }
    }
}
