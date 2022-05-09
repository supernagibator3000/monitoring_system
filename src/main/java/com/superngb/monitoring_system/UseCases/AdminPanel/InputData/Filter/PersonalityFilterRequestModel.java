package com.superngb.monitoring_system.UseCases.AdminPanel.InputData.Filter;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PersonalityFilterRequestModel {

    private Long id;
    private String firstName;
    private String secondName;
    private String middleName;
}
