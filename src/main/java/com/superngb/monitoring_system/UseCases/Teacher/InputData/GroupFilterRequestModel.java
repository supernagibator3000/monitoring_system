package com.superngb.monitoring_system.UseCases.Teacher.InputData;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GroupFilterRequestModel {

    private Long id;
    private String name;
}
