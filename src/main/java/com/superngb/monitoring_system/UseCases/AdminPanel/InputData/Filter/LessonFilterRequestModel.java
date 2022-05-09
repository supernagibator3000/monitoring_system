package com.superngb.monitoring_system.UseCases.AdminPanel.InputData.Filter;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LessonFilterRequestModel {

    private Long id;
    private String name;
    private Long subjectId;
}
