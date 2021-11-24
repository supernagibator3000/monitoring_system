package com.superngb.monitoring_system.UseCases.AdminPanel.InputData;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentPostRequestModel {

    private Long personalityId;
    private String studentCardId;
    private String groupName;
}
