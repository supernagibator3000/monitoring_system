package com.superngb.monitoring_system.UseCases.AdminPanel.InputData;

import lombok.Data;

@Data
public class FilterPersonalityRequestModel {

    private Long id;
    private String firstName;
    private String secondName;
    private String middleName;
}
