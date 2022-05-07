package com.superngb.monitoring_system.UseCases.Teacher.DataAccess;

import com.superngb.monitoring_system.Entities.Subject;

import java.util.List;

public interface TeacherSubjectDataAccess {
    List<Subject> getAll();
    List<Subject> filter(Long id, String name);
    Subject findById(Long id);
    Subject findByName(String name);
    void save(Subject subject);
    Subject deleteById(Long id);
}
