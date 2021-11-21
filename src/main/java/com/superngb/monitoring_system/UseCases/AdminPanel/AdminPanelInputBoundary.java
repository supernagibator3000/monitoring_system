package com.superngb.monitoring_system.UseCases.AdminPanel;

import com.superngb.monitoring_system.DTOs.person.UserDtoModel;
import com.superngb.monitoring_system.UseCases.AdminPanel.InputData.EditUserRequestModel;
import com.superngb.monitoring_system.UseCases.AdminPanel.InputData.FilterUserRequestModel;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface AdminPanelInputBoundary {
    List<UserDtoModel> getAll();
    UserDtoModel findConcreteUser(Long id);
    boolean editUser(Long id, EditUserRequestModel editUserRequestModel);
    List<UserDtoModel> filterUsers(FilterUserRequestModel filterUserRequestModel);
    UserDtoModel deleteUser(Long id);
}
