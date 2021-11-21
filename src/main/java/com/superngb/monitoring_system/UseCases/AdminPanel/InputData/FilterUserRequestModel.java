package com.superngb.monitoring_system.UseCases.AdminPanel.InputData;

import lombok.Data;

@Data
public class FilterUserRequestModel {

    private Long id;
    private String username;
    private String roleUser;
    private String roleAdmin;
}
