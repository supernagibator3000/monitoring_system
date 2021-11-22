package com.superngb.monitoring_system.UseCases.AdminPanel.InputData;

import lombok.Data;

@Data
public class UserEditRequestModel {

    private String username;
    private String roleUser;
    private String roleAdmin;
}
