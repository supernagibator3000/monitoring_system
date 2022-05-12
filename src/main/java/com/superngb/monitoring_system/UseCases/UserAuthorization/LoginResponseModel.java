package com.superngb.monitoring_system.UseCases.UserAuthorization;

import com.superngb.monitoring_system.DTOs.person.UserDtoModel;

public class LoginResponseModel {
    public UserDtoModel userDtoModel;
    public String token;
    public String response;

    public LoginResponseModel(UserDtoModel userDtoModel, String token) {
        this.userDtoModel = userDtoModel;
        this.token = token;
    }

    public LoginResponseModel(String response) {
        this.response = response;
    }
}
