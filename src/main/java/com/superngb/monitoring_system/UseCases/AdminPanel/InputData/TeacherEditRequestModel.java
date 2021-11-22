package com.superngb.monitoring_system.UseCases.AdminPanel.InputData;

import lombok.Data;

import java.util.List;

@Data
public class TeacherEditRequestModel {

    private List<Long> subjects;
}
