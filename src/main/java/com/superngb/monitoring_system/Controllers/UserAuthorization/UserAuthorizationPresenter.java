package com.superngb.monitoring_system.Controllers.UserAuthorization;

import com.superngb.monitoring_system.UseCases.UserAuthorization.RegistrationResponseModel;
import com.superngb.monitoring_system.UseCases.UserAuthorization.UserAuthorizationOutputBoundary;

public class UserAuthorizationPresenter implements UserAuthorizationOutputBoundary {
    @Override
    public RegistrationResponseModel prepareSuccessView() {
        return new RegistrationResponseModel(false, false);
    }

    @Override
    public RegistrationResponseModel prepareFailView(RegistrationResponseModel registrationResponseModel) {
        return registrationResponseModel;
    }
}
