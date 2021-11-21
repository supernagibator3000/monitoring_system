package com.superngb.monitoring_system.UseCases.AdminPanel;

import com.superngb.monitoring_system.DTOs.person.PersonalityDtoModel;
import com.superngb.monitoring_system.DTOs.person.UserDtoModel;
import com.superngb.monitoring_system.Entities.Role;
import com.superngb.monitoring_system.Entities.person.Personality;
import com.superngb.monitoring_system.Entities.person.User;
import com.superngb.monitoring_system.Enums.RoleEnum;
import com.superngb.monitoring_system.UseCases.AdminPanel.DataAccessInterfaces.AdminPanelPersonalityDataAccess;
import com.superngb.monitoring_system.UseCases.AdminPanel.DataAccessInterfaces.AdminPanelRoleDataAccess;
import com.superngb.monitoring_system.UseCases.AdminPanel.DataAccessInterfaces.AdminPanelUserDataAccess;
import com.superngb.monitoring_system.UseCases.AdminPanel.InputData.EditPersonalityRequestModel;
import com.superngb.monitoring_system.UseCases.AdminPanel.InputData.EditUserRequestModel;
import com.superngb.monitoring_system.UseCases.AdminPanel.InputData.FilterPersonalityRequestModel;
import com.superngb.monitoring_system.UseCases.AdminPanel.InputData.FilterUserRequestModel;

import java.util.List;

public class AdminPanelInteractor implements AdminPanelInputBoundary{
    private AdminPanelUserDataAccess adminPanelUserDataAccess;
    private AdminPanelRoleDataAccess adminPanelRoleDataAccess;
    private AdminPanelPersonalityDataAccess adminPanelPersonalityDataAccess;

    private AdminPanelOutputBoundary adminPanelOutputBoundary;

    public AdminPanelInteractor(AdminPanelUserDataAccess adminPanelUserDataAccess, AdminPanelRoleDataAccess adminPanelRoleDataAccess, AdminPanelPersonalityDataAccess adminPanelPersonalityDataAccess, AdminPanelOutputBoundary adminPanelOutputBoundary) {
        this.adminPanelUserDataAccess = adminPanelUserDataAccess;
        this.adminPanelRoleDataAccess = adminPanelRoleDataAccess;
        this.adminPanelPersonalityDataAccess = adminPanelPersonalityDataAccess;
        this.adminPanelOutputBoundary = adminPanelOutputBoundary;
    }

    @Override
    public List<UserDtoModel> getUsers() {
        List<User> users = adminPanelUserDataAccess.getAll();
        List<UserDtoModel> userDtoModels = UserDtoModel.listUsersMapper(users);
        return adminPanelOutputBoundary.convertUsers(userDtoModels);
    }

    @Override
    public UserDtoModel findUser(Long id) {
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

    @Override
    public List<PersonalityDtoModel> getPersonalities() {
        List<Personality> personalityList = adminPanelPersonalityDataAccess.getAll();
        List<PersonalityDtoModel> personalityDtoModelList = PersonalityDtoModel.listPersonalitiesMapper(personalityList);
        return personalityDtoModelList;
    }

    @Override
    public PersonalityDtoModel findPersonality(Long id) {
        Personality personality = adminPanelPersonalityDataAccess.findById(id);
        PersonalityDtoModel personalityDtoModel = PersonalityDtoModel.personalityMapper(personality);
        return personalityDtoModel;
    }

    @Override
    public boolean editPersonality(Long id, EditPersonalityRequestModel editPersonalityRequestModel) {
        Personality personality = adminPanelPersonalityDataAccess.findById(id);
        Personality personalityByEmail = adminPanelPersonalityDataAccess.findByEmail(editPersonalityRequestModel.getEmail());

        if(personalityByEmail!=null && personalityByEmail!=personality)
            return adminPanelOutputBoundary.prepareFailEditPersonalityView(PersonalityDtoModel.personalityMapper(personality));

        if (!editPersonalityRequestModel.getEmail().equals(""))
            personality.setFirstName(editPersonalityRequestModel.getEmail());

        if(!editPersonalityRequestModel.getFirstName().equals(""))
            personality.setFirstName(editPersonalityRequestModel.getFirstName());

        if(!editPersonalityRequestModel.getSecondName().equals(""))
            personality.setSecondName(editPersonalityRequestModel.getSecondName());

        if(!editPersonalityRequestModel.getMiddleName().equals(""))
            personality.setMiddleName(editPersonalityRequestModel.getMiddleName());

        adminPanelPersonalityDataAccess.save(personality);
        PersonalityDtoModel personalityDtoModel = PersonalityDtoModel.personalityMapper(personality);
        return adminPanelOutputBoundary.prepareSuccessEditPersonalityView(personalityDtoModel);
    }

    @Override
    public List<PersonalityDtoModel> filterPersonalities(FilterPersonalityRequestModel filterPersonalityModel) {
        List<Personality> personalityList = adminPanelPersonalityDataAccess.filter(filterPersonalityModel.getId(),
                filterPersonalityModel.getFirstName(),
                filterPersonalityModel.getSecondName(),
                filterPersonalityModel.getMiddleName());
        List<PersonalityDtoModel> personalityDtoModels = PersonalityDtoModel.listPersonalitiesMapper(personalityList);
        return adminPanelOutputBoundary.convertPersonalities(personalityDtoModels);
    }

    @Override
    public PersonalityDtoModel deletePersonality(Long id) {
        // delete user+teacher+student
        return null;
    }
}
