package com.superngb.monitoring_system.Repositories.person;

import com.superngb.monitoring_system.Entities.person.Personality;
import com.superngb.monitoring_system.Entities.person.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long> {
    Student findByStudentCardId(String studentCardId);
    Student findByPersonalityId(Long personalityId);
    Student findByGroupId(Long groupId);
    List<Student> findAllByGroupId(Long group);
}
