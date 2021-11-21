package com.superngb.monitoring_system.UseCases.AdminPanel;

import com.superngb.monitoring_system.DTOs.person.UserDtoModel;
import com.superngb.monitoring_system.Entities.Role;
import com.superngb.monitoring_system.Entities.person.User;
import com.superngb.monitoring_system.Enums.RoleEnum;
import com.superngb.monitoring_system.UseCases.AdminPanel.DataAccessInterfaces.AdminPanelRoleDataAccess;
import com.superngb.monitoring_system.UseCases.AdminPanel.DataAccessInterfaces.AdminPanelUserDataAccess;
import com.superngb.monitoring_system.UseCases.AdminPanel.InputData.EditUserRequestModel;
import com.superngb.monitoring_system.UseCases.AdminPanel.InputData.FilterUserRequestModel;

import java.util.List;

public class AdminPanelInteractor implements AdminPanelInputBoundary{
    AdminPanelUserDataAccess adminPanelUserDataAccess;
    AdminPanelRoleDataAccess adminPanelRoleDataAccess;

    AdminPanelOutputBoundary adminPanelOutputBoundary;

    public AdminPanelInteractor(AdminPanelUserDataAccess adminPanelUserDataAccess, AdminPanelRoleDataAccess adminPanelRoleDataAccess, AdminPanelOutputBoundary adminPanelOutputBoundary) {
        this.adminPanelUserDataAccess = adminPanelUserDataAccess;
        this.adminPanelRoleDataAccess = adminPanelRoleDataAccess;
        this.adminPanelOutputBoundary = adminPanelOutputBoundary;
    }

    @Override
    public List<UserDtoModel> getAll() {
        List<User> users = adminPanelUserDataAccess.getAll();
        List<UserDtoModel> userDtoModels = UserDtoModel.listUsersMapper(users);
        return adminPanelOutputBoundary.convertUsers(userDtoModels);
    }

    @Override
    public UserDtoModel findConcreteUser(Long id) {
        User user = adminPanelUserDataAccess.findById(id);
        UserDtoModel userDtoModel = UserDtoModel.userMapper(user);
        return adminPanelOutputBoundary.prepareFindedUserView(userDtoModel);
    }

    @Override
    public boolean editUser(Long id, EditUserRequestModel editUserRequestModel) {
        User user = adminPanelUserDataAccess.findById(id);
        User userByName = adminPanelUserDataAccess.findByUsername(editUserRequestModel.getUsername());
        if(userByName!=null && userByName!=user)
            return adminPanelOutputBoundary.prepareFailEditUserView(UserDtoModel.userMapper(user));

        if (!editUserRequestModel.getUsername().equals("")) user.setUsername(editUserRequestModel.getUsername());

        List<Role> roles = user.getRoles();
        boolean alreadyExistRoleUser = false;
        boolean alreadyExistRoleAdmin = false;
        for (Role role:user.getRoles()){
            if (role.getName().equals(RoleEnum.ROLE_USER.name())) {
                alreadyExistRoleUser = true;
            }
            if (role.getName().equals(RoleEnum.ROLE_ADMIN.name())) {
                alreadyExistRoleAdmin = true;
            }
        }
        if (!editUserRequestModel.getRoleUser().equals("")){
            if (!alreadyExistRoleUser) roles.add(adminPanelRoleDataAccess.findById(RoleEnum.ROLE_USER.getId()));
        }
        else{
            if(alreadyExistRoleUser) roles.remove(adminPanelRoleDataAccess.findById(RoleEnum.ROLE_USER.getId()));
        }
        if (!editUserRequestModel.getRoleAdmin().equals("")){
            if (!alreadyExistRoleAdmin) roles.add(adminPanelRoleDataAccess.findById(RoleEnum.ROLE_ADMIN.getId()));
        }
        else{
            if (alreadyExistRoleAdmin) roles.remove(adminPanelRoleDataAccess.findById(RoleEnum.ROLE_ADMIN.getId()));
        }
        user.setRoles(roles);
        adminPanelUserDataAccess.save(user);
        UserDtoModel userDtoModel = UserDtoModel.userMapper(user);
        return adminPanelOutputBoundary.prepareSuccessEditUserView(userDtoModel);
    }

    @Override
    public List<UserDtoModel> filterUsers(FilterUserRequestModel filterUserRequestModel) {
        List<User> users = adminPanelUserDataAccess.filter(filterUserRequestModel.getId(),
                filterUserRequestModel.getUsername(),
                filterUserRequestModel.getRoleUser(),
                filterUserRequestModel.getRoleAdmin());
        List<UserDtoModel> userDtoModels = UserDtoModel.listUsersMapper(users);
        return adminPanelOutputBoundary.convertUsers(userDtoModels);
    }

    @Override
    public UserDtoModel deleteUser(Long id) {
        User user = adminPanelUserDataAccess.deleteById(id);
        UserDtoModel userDtoModel = UserDtoModel.userMapper(user);
        return adminPanelOutputBoundary.prepareDeletedUserView(userDtoModel);
    }
}
