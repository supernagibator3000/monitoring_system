package com.superngb.monitoring_system.UseCases.AdminPanel.DataAccess;

import com.superngb.monitoring_system.Entities.mark.Attendance;

import java.util.List;

public interface AdminPanelAttendanceDataAccess {
    List<Attendance> getAll();
    List<Attendance> filter(Long id, Long studentId, Long lessonId);
    Attendance findById(Long id);
    Attendance findByLessonIdAndStudentId(Long lessonId, Long studentId);
    List<Attendance> findAllByStudentId(Long studentId);
    List<Attendance> findAllByLessonId(Long lessonId);
    void save(Attendance attendance);
    Attendance deleteById(Long id);
}
