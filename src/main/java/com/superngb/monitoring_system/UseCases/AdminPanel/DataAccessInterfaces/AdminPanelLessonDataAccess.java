package com.superngb.monitoring_system.UseCases.AdminPanel.DataAccessInterfaces;

import com.superngb.monitoring_system.Entities.event.Lesson;

import java.util.List;

public interface AdminPanelLessonDataAccess {
    List<Lesson> getAll();
    List<Lesson> filter(Long id, String name, Long subjectId);
    Lesson findById(Long id);
    Lesson findByName(String name);
    Lesson findBySubjectId(Long subjectId);
    void save(Lesson lesson);
    Lesson deleteById(Long id);
}
