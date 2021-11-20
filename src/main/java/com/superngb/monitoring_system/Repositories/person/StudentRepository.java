package com.superngb.monitoring_system.Repositories.person;

import com.superngb.monitoring_system.Entities.person.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {
}
