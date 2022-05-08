package com.superngb.monitoring_system.UseCases.Teacher.DataAccess;

import com.superngb.monitoring_system.Entities.person.Personality;

import java.util.List;

public interface TeacherPersonalityDataAccess {
    List<Personality> getAll();
    List<Personality> filter(Long id, String firstName, String secondName, String middleName);
    Personality findById(Long id);
    Personality findByFirstName(String firstName);
    Personality findBySecondName(String secondName);
    Personality findByMiddleName(String middleName);
    Personality findByEmail(String email);
}
