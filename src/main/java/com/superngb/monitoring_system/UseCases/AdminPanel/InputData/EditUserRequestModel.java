package com.superngb.monitoring_system.UseCases.AdminPanel.InputData;

import lombok.Data;

@Data
public class EditUserRequestModel {

    private String username;
    private String roleUser;
    private String roleAdmin;
}
