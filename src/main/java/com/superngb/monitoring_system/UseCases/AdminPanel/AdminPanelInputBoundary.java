package com.superngb.monitoring_system.UseCases.AdminPanel;

import com.superngb.monitoring_system.DTOs.person.PersonalityDtoModel;
import com.superngb.monitoring_system.DTOs.person.UserDtoModel;
import com.superngb.monitoring_system.UseCases.AdminPanel.InputData.EditPersonalityRequestModel;
import com.superngb.monitoring_system.UseCases.AdminPanel.InputData.EditUserRequestModel;
import com.superngb.monitoring_system.UseCases.AdminPanel.InputData.FilterPersonalityRequestModel;
import com.superngb.monitoring_system.UseCases.AdminPanel.InputData.FilterUserRequestModel;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface AdminPanelInputBoundary {
    List<UserDtoModel> getUsers();
    UserDtoModel findUser(Long id);
    boolean editUser(Long id, EditUserRequestModel editUserRequestModel);
    List<UserDtoModel> filterUsers(FilterUserRequestModel filterUserRequestModel);
    UserDtoModel deleteUser(Long id);

    List<PersonalityDtoModel> getPersonalities();
    PersonalityDtoModel findPersonality(Long id);
    boolean editPersonality(Long id, EditPersonalityRequestModel editPersonalityRequestModel);
    List<PersonalityDtoModel> filterPersonalities(FilterPersonalityRequestModel filterPersonalityModel);
    PersonalityDtoModel deletePersonality(Long id);

}
