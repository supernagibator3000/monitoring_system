package com.superngb.monitoring_system.UseCases.AdminPanel.InputData;

import lombok.Data;

@Data
public class CheckpointFilterRequestModel {

    private Long id;
    private String name;
    private Long subjectId;
}
