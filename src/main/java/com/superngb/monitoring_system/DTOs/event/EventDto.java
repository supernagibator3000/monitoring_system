package com.superngb.monitoring_system.DTOs.event;

import lombok.Data;

import java.io.Serializable;

@Data
public class EventDto implements Serializable {

    private Long id;
    private String name;
    private Long subject;
}
