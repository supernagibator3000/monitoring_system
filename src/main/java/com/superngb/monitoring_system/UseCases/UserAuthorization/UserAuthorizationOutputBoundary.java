package com.superngb.monitoring_system.UseCases.UserAuthorization;

public interface UserAuthorizationOutputBoundary {
    RegistrationResponseModel prepareSuccessView();
    RegistrationResponseModel prepareFailView(RegistrationResponseModel registrationResponseModel);
}
