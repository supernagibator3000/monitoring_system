package com.superngb.monitoring_system.Controllers.UserAuthorization;

import com.superngb.monitoring_system.UseCases.UserAuthorization.RegistrationResponseModel;
import com.superngb.monitoring_system.UseCases.UserAuthorization.UserAuthorizationOutputBoundary;

public class UserAuthorizationPresenter implements UserAuthorizationOutputBoundary {
    @Override
    public RegistrationResponseModel prepareSuccessRegistrationView() {
        return new RegistrationResponseModel(false, false);
    }

    @Override
    public RegistrationResponseModel prepareFailRegistrationView(RegistrationResponseModel registrationResponseModel) {
        return registrationResponseModel;
    }
}
