package com.superngb.monitoring_system.DTOs.person;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class TeacherDto implements Serializable {

    private Long id;
    private Long personality;
    private List<Long> subjects;
}
