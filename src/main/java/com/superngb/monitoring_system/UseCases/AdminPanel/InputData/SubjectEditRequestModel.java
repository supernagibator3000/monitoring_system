package com.superngb.monitoring_system.UseCases.AdminPanel.InputData;

import lombok.Data;

import java.util.List;

@Data
public class SubjectEditRequestModel {

    private String name;
    private List<Long> groups;
    private List<Long> teachers;
}
