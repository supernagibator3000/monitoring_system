package com.superngb.monitoring_system.UseCases.AdminPanel.InputData.Edit;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SubjectEditRequestModel {

    private String name;
    private List<Long> groups;
    private List<Long> teachers;
}
