package com.superngb.monitoring_system.Controllers.AdminPanel;

import com.superngb.monitoring_system.DTOs.person.PersonalityDtoModel;
import com.superngb.monitoring_system.DTOs.person.UserDtoModel;
import com.superngb.monitoring_system.UseCases.AdminPanel.AdminPanelOutputBoundary;

import java.util.List;

public class AdminPanelPresenter implements AdminPanelOutputBoundary {

    @Override
    public boolean prepareSuccessEditUserView(UserDtoModel userDtoModel) {
        return true;
    }

    @Override
    public boolean prepareFailEditUserView(UserDtoModel userDtoModel) {
        return false;
    }

    @Override
    public UserDtoModel prepareDeletedUserView(UserDtoModel userDtoModel) {
        return userDtoModel;
    }

    @Override
    public UserDtoModel prepareFindedUserView(UserDtoModel userDtoModel) {
        return userDtoModel;
    }

    @Override
    public List<UserDtoModel> convertUsers(List<UserDtoModel> users) {
        return users;
    }

    @Override
    public boolean prepareSuccessEditPersonalityView(PersonalityDtoModel personalityDtoModel) {
        return true;
    }

    @Override
    public boolean prepareFailEditPersonalityView(PersonalityDtoModel personalityDtoModel) {
        return false;
    }

    @Override
    public PersonalityDtoModel prepareDeletedPersonalityView(PersonalityDtoModel personalityDtoModel) {
        return personalityDtoModel;
    }

    @Override
    public PersonalityDtoModel prepareFindedPersonalityView(PersonalityDtoModel personalityDtoModel) {
        return personalityDtoModel;
    }

    @Override
    public List<PersonalityDtoModel> convertPersonalities(List<PersonalityDtoModel> personalityDtoModels) {
        return personalityDtoModels;
    }
}
