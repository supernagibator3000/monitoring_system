package com.superngb.monitoring_system.Repositories.mark;

import com.superngb.monitoring_system.Entities.mark.Score;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ScoreRepository extends JpaRepository<Score, Long> {
    List<Score> findAllByStudentId(Long studentId);
    List<Score> findAllByCheckpointId(Long checkpointId);
    Score findByCheckpointIdAndStudentId(Long checkpointId, Long studentId);
}
