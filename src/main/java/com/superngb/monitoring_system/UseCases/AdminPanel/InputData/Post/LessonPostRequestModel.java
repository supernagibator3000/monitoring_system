package com.superngb.monitoring_system.UseCases.AdminPanel.InputData.Post;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LessonPostRequestModel {

    private String name;
    private Long subjectId;
}
