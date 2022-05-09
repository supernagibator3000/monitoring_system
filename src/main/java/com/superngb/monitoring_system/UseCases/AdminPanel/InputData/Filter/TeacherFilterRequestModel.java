package com.superngb.monitoring_system.UseCases.AdminPanel.InputData.Filter;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TeacherFilterRequestModel {

    private Long id;
    private Long personalityId;
}
