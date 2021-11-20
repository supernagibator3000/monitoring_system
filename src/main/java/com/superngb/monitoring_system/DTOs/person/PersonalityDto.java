package com.superngb.monitoring_system.DTOs.person;

import lombok.Data;

import java.io.Serializable;

@Data
public class PersonalityDto implements Serializable {

    private Long id;
    private String firstName;
    private String secondName;
    private String middleName;
    private String email;
}
