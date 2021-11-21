package com.superngb.monitoring_system.UseCases.AdminPanel.InputData;

import lombok.Data;

@Data
public class EditPersonalityRequestModel {

    private String firstName;
    private String secondName;
    private String middleName;
    private String email;
}
