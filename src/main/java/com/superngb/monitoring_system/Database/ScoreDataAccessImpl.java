package com.superngb.monitoring_system.Database;

import com.superngb.monitoring_system.Entities.event.Checkpoint;
import com.superngb.monitoring_system.Entities.mark.Score;
import com.superngb.monitoring_system.Entities.person.Student;
import com.superngb.monitoring_system.Repositories.mark.ScoreRepository;
import com.superngb.monitoring_system.UseCases.AdminPanel.DataAccess.AdminPanelScoreDataAccess;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.*;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ScoreDataAccessImpl implements AdminPanelScoreDataAccess {

    private ScoreRepository scoreRepository;

    @PersistenceContext
    private EntityManager entityManager;

    public ScoreDataAccessImpl(ScoreRepository scoreRepository) {
        this.scoreRepository = scoreRepository;
    }

    @Override
    public List<Score> getAll() {
        return scoreRepository.findAll();
    }

    @Override
    public List<Score> filter(Long id, Long studentId, Long checkpointId) {
        if (id == null && studentId == null && checkpointId == null){
            return getAll();
        }
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Score> criteriaQuery = criteriaBuilder.createQuery(Score.class);
        Root<Score> score = criteriaQuery.from(Score.class);

        Join<Score, Student> studentJoin = score.join("student", JoinType.INNER);
        Join<Score, Checkpoint> checkpointJoin = score.join("checkpoint", JoinType.INNER);

        Predicate predicateForId = criteriaBuilder.equal(score.get("id"), id);
        Predicate predicateForStudent = criteriaBuilder.equal(studentJoin.get("id").as(Long.class), studentId);
        Predicate predicateForCheckpoint = criteriaBuilder.equal(checkpointJoin.get("id").as(Long.class), checkpointId);

        Predicate predicate = null;

        if(id!=null){
            predicate = criteriaBuilder.and(predicateForId);
        }
        if(studentId != null){
            if (predicate != null)
                predicate = criteriaBuilder.and(predicate,predicateForStudent);
            else
                predicate = predicateForStudent;
        }
        if(checkpointId != null){
            if (predicate != null)
                predicate = criteriaBuilder.and(predicate,predicateForCheckpoint);
            else
                predicate = predicateForCheckpoint;
        }

        criteriaQuery.select(score).where(predicate);
        List<Score> scoreList = entityManager.createQuery(criteriaQuery).getResultList();

        Set<Score> scoreSet = new HashSet<>(scoreList);
        scoreList.clear();
        scoreList.addAll(scoreSet);

        scoreList.sort(Comparator.comparingLong(Score::getId));

        return scoreList;
    }

    @Override
    public Score findById(Long id) {
        return scoreRepository.findById(id).orElse(null);
    }

    @Override
    public Score findByCheckpointIdAndStudentId(Long checkpointId, Long studentId) {
        return scoreRepository.findByCheckpointIdAndStudentId(checkpointId, studentId);
    }

    @Override
    public List<Score> findAllByStudentId(Long studentId) {
        return scoreRepository.findAllByStudentId(studentId);
    }

    @Override
    public List<Score> findAllByLessonId(Long checkpointId) {
        return scoreRepository.findAllByCheckpointId(checkpointId);
    }

    @Override
    public void save(Score score) {
        scoreRepository.save(score);
    }

    @Override
    public Score deleteById(Long id) {
        Score score = scoreRepository.findById(id).orElse(null);
        if (score!=null){
            scoreRepository.deleteById(id);
            return score;
        }
        else return null;
    }
}
