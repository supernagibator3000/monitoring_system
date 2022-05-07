package com.superngb.monitoring_system.UseCases.Teacher.DataAccess;

import com.superngb.monitoring_system.Entities.mark.Score;

import java.util.List;

public interface TeacherScoreDataAccess {
    List<Score> getAll();
    List<Score> filter(Long id, Long studentId, Long checkpointId);
    Score findById(Long id);
    Score findByCheckpointIdAndStudentId(Long checkpointId, Long studentId);
    List<Score> findAllByStudentId(Long studentId);
    List<Score> findAllByLessonId(Long checkpointId);
    void save(Score score);
    Score deleteById(Long id);
}
