package com.superngb.monitoring_system.UseCases.UserAuthorization;

import lombok.Data;

@Data
public class RegistrationRequest {
    public String username;
    public String password;
    public String passwordConfirm;
}
