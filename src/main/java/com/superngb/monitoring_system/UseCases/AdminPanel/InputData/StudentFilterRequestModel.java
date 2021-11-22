package com.superngb.monitoring_system.UseCases.AdminPanel.InputData;

import lombok.Data;

@Data
public class StudentFilterRequestModel {

    private Long id;
    private String studentCardId;
    private Long personalityId;
    private Long groupId;
}
