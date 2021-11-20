package com.superngb.monitoring_system.Repositories.mark;

import com.superngb.monitoring_system.Entities.mark.Score;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScoreRepository extends JpaRepository<Score, Long> {
}
