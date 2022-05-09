package com.superngb.monitoring_system.UseCases.Teacher.InputData.Post;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AttendancePostRequestModel {

    private Long studentId;
    private String mark;
    private Long lessonId;
}
