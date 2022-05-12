package com.superngb.monitoring_system.UseCases.UserAuthorization;

public interface UserAuthorizationOutputBoundary {
    RegistrationResponseModel prepareSuccessRegistrationView();
    RegistrationResponseModel prepareFailRegistrationView(RegistrationResponseModel registrationResponseModel);
}
