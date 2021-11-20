package com.superngb.monitoring_system.Repositories;

import com.superngb.monitoring_system.Entities.Subject;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubjectRepository extends JpaRepository<Subject, Long> {
}
