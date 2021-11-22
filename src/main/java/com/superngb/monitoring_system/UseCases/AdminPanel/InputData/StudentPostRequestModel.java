package com.superngb.monitoring_system.UseCases.AdminPanel.InputData;

import lombok.Data;

@Data
public class StudentPostRequestModel {

    private Long personalityId;
    private String studentCardId;
    private String groupName;
}
