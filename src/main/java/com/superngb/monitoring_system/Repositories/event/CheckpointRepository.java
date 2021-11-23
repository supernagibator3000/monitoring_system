package com.superngb.monitoring_system.Repositories.event;

import com.superngb.monitoring_system.Entities.Subject;
import com.superngb.monitoring_system.Entities.event.Checkpoint;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CheckpointRepository extends JpaRepository<Checkpoint, Long> {
    Checkpoint findByName(String name);
    Checkpoint findBySubjectId(Long subjectId);
}
