package com.superngb.monitoring_system.Entities.person;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "personalities")
public class Personality {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "second_name")
    private String secondName;

    @Column(name = "middle_name")
    private String middleName;

    @Column(name = "email")
    private String email;
}

