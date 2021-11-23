package com.superngb.monitoring_system.UseCases.AdminPanel.InputData;

import lombok.Data;

@Data
public class ScoreFilterRequestModel {

    private Long id;
    private Long studentId;
    private Long checkpointId;
}
