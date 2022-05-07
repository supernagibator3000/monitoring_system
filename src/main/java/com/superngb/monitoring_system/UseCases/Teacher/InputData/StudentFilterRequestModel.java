package com.superngb.monitoring_system.UseCases.Teacher.InputData;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentFilterRequestModel {

    private Long id;
    private String studentCardId;
    private Long personalityId;
    private Long groupId;
}
