package com.superngb.monitoring_system.Repositories.event;

import com.superngb.monitoring_system.Entities.event.Lesson;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LessonRepository extends JpaRepository<Lesson, Long> {
}
