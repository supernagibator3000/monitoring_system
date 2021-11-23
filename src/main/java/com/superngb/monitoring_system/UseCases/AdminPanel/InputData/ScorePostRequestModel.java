package com.superngb.monitoring_system.UseCases.AdminPanel.InputData;

import lombok.Data;

@Data
public class ScorePostRequestModel {

    private Long studentId;
    private String mark;
    private Long checkpointId;
}
