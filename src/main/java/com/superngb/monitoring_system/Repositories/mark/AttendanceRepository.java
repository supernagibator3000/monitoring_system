package com.superngb.monitoring_system.Repositories.mark;

import com.superngb.monitoring_system.Entities.mark.Attendance;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AttendanceRepository extends JpaRepository<Attendance, Long> {
    List<Attendance> findAllByStudentId(Long studentId);
    List<Attendance> findAllByLessonId(Long lessonId);
    Attendance findByLessonIdAndStudentId(Long lessonId, Long studentId);
}
