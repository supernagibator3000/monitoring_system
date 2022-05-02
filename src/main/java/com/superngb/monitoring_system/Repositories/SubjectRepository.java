package com.superngb.monitoring_system.Repositories;

import com.superngb.monitoring_system.Entities.Group;
import com.superngb.monitoring_system.Entities.Subject;
import org.apache.catalina.startup.ListenerCreateRule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SubjectRepository extends JpaRepository<Subject, Long> {
    Subject findByName(String name);
}
