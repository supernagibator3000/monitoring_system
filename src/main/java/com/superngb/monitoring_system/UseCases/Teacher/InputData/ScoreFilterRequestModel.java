package com.superngb.monitoring_system.UseCases.Teacher.InputData;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ScoreFilterRequestModel {

    private Long id;
    private Long studentId;
    private Long checkpointId;
}
