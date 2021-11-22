package com.superngb.monitoring_system.UseCases.AdminPanel.InputData;

import lombok.Data;

@Data
public class UserPostRequestModel {

    private Long personalityId;
    private String username;
    private String password;
    private String role;
}
