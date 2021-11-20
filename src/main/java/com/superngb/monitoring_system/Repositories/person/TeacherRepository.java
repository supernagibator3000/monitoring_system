package com.superngb.monitoring_system.Repositories.person;

import com.superngb.monitoring_system.Entities.person.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeacherRepository extends JpaRepository<Teacher, Long> {
}
