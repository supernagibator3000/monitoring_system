package com.superngb.monitoring_system.UseCases.AdminPanel.InputData;

import lombok.Data;

@Data
public class AttendanceFilterRequestModel {

    private Long id;
    private Long studentId;
    private Long lessonId;
}
