package com.superngb.monitoring_system.DTOs.event;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class CheckpointDto implements Serializable {

    private Long id;
    private Long event;
    private List<Long> scoreList;
}
