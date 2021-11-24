package com.superngb.monitoring_system.UseCases.AdminPanel.DataAccess;

import com.superngb.monitoring_system.Entities.person.Student;

import java.util.List;

public interface AdminPanelStudentDataAccess {
    List<Student> getAll();
    List<Student> filter(Long id, String studentCardId, Long personalityId, Long groupId);
    Student findById(Long id);
    Student findByStudentCardId(String studentCardId);
    Student findByPersonalityId(Long personalityId);
    Student findByGroupId(Long groupId);
    List<Student> findAllByGroupId(Long id);
    void save(Student student);
    Student deleteById(Long id);
}
