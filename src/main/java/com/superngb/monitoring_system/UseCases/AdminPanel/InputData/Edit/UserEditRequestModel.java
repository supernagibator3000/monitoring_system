package com.superngb.monitoring_system.UseCases.AdminPanel.InputData.Edit;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserEditRequestModel {

    private String username;
    private String roleUser;
    private String roleAdmin;
}
