package com.superngb.monitoring_system.UseCases.AdminPanel.InputData;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PersonalityEditRequestModel {

    private String firstName;
    private String secondName;
    private String middleName;
    private String email;
}
