package com.superngb.monitoring_system.DTOs.mark;

import lombok.Data;

import java.io.Serializable;

@Data
public class ScoreDto implements Serializable {

    private Long id;
    private Long student;
    private String scoreMark;
    private Long checkpoint;
}
