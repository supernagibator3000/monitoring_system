package com.superngb.monitoring_system.UseCases.Teacher.DataAccess;

import com.superngb.monitoring_system.Entities.event.Lesson;

import java.util.List;

public interface TeacherLessonDataAccess {
    List<Lesson> getAll();
    List<Lesson> filter(Long id, String name, Long subjectId);
    Lesson findById(Long id);
    Lesson findByName(String name);
    Lesson findBySubjectId(Long subjectId);
    void save(Lesson lesson);
    Lesson deleteById(Long id);
}
