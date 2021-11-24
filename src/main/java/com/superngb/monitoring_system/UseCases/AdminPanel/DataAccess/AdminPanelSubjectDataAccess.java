package com.superngb.monitoring_system.UseCases.AdminPanel.DataAccess;

import com.superngb.monitoring_system.Entities.Subject;

import java.util.List;

public interface AdminPanelSubjectDataAccess {
    List<Subject> getAll();
    List<Subject> filter(Long id, String name);
    Subject findById(Long id);
    Subject findByName(String name);
//    List<Subject> findAllByGroups(List<Group> groups);
    void save(Subject subject);
    Subject deleteById(Long id);
}
