package com.superngb.monitoring_system.UseCases.Teacher.DataAccess;

import com.superngb.monitoring_system.Entities.Group;

import java.util.List;

public interface TeacherGroupDataAccess {
    List<Group> getAll();
    List<Group> filter(Long id, String name);
    Group findById(Long id);
    Group findByName(String name);
    void save(Group group);
    Group deleteById(Long id);
}
