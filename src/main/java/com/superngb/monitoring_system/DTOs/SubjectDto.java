package com.superngb.monitoring_system.DTOs;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class SubjectDto implements Serializable {

    private Long id;
    private String name;
    private List<Long> events;
    private List<Long> groups;
    private List<Long> students;
    private List<Long> teachers;
}
