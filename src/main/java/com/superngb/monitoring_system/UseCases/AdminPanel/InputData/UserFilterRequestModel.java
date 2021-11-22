package com.superngb.monitoring_system.UseCases.AdminPanel.InputData;

import lombok.Data;

@Data
public class UserFilterRequestModel {

    private Long id;
    private Long personality;
    private String username;
    private String roleUser;
    private String roleAdmin;
}
