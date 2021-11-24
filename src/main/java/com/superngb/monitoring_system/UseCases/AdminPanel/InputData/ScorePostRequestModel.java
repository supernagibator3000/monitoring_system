package com.superngb.monitoring_system.UseCases.AdminPanel.InputData;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ScorePostRequestModel {

    private Long studentId;
    private String mark;
    private Long checkpointId;
}
