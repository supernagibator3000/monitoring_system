package com.superngb.monitoring_system.UseCases.Teacher.InputData.Filter;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AttendanceFilterRequestModel {

    private Long id;
    private Long studentId;
    private Long lessonId;
}
