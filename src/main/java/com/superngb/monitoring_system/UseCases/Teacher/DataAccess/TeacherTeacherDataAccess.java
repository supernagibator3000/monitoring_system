package com.superngb.monitoring_system.UseCases.Teacher.DataAccess;

import com.superngb.monitoring_system.Entities.person.Teacher;

import java.util.List;

public interface TeacherTeacherDataAccess {
    List<Teacher> getAll();
    List<Teacher> filter(Long id, Long personality);
    Teacher findById(Long id);
    void save(Teacher teacher);
    Teacher deleteById(Long id);
    Teacher findByPersonalityId(Long personality);
}
