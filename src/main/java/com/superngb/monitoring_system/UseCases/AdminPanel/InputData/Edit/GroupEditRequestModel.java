package com.superngb.monitoring_system.UseCases.AdminPanel.InputData.Edit;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GroupEditRequestModel {

    private String name;
    private String studentCardId;
}
