package com.superngb.monitoring_system.DTOs.person;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class StudentDto implements Serializable {

    private Long id;
    private Long personality;
    private String studentCardId;
    private Long group;
    private List<Long> subjects;
    private List<Long> scores;
    private List<Long> attendance;
}
