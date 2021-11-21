package com.superngb.monitoring_system.UseCases.AdminPanel;

import com.superngb.monitoring_system.DTOs.person.UserDtoModel;

import java.util.List;

public interface AdminPanelOutputBoundary {
    boolean prepareSuccessEditUserView(UserDtoModel userDtoModel);
    boolean prepareFailEditUserView(UserDtoModel userDtoModel);
    UserDtoModel prepareDeletedUserView(UserDtoModel userDtoModel);
    UserDtoModel prepareFindedUserView(UserDtoModel userDtoModel);
    List<UserDtoModel> convertUsers(List<UserDtoModel> users);
}
