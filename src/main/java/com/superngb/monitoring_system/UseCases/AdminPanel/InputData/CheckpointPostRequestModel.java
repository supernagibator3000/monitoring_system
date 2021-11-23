package com.superngb.monitoring_system.UseCases.AdminPanel.InputData;

import lombok.Data;

@Data
public class CheckpointPostRequestModel {

    private String name;
    private Long subjectId;
}
