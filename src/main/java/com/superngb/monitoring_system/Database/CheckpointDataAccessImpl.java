package com.superngb.monitoring_system.Database;

import com.superngb.monitoring_system.Entities.Subject;
import com.superngb.monitoring_system.Entities.event.Checkpoint;
import com.superngb.monitoring_system.Repositories.event.CheckpointRepository;
import com.superngb.monitoring_system.UseCases.AdminPanel.DataAccessInterfaces.AdminPanelCheckpointDataAccess;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.*;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CheckpointDataAccessImpl implements AdminPanelCheckpointDataAccess {

    private CheckpointRepository checkpointRepository;

    @PersistenceContext
    private EntityManager entityManager;

    public CheckpointDataAccessImpl(CheckpointRepository checkpointRepository) {
        this.checkpointRepository = checkpointRepository;
    }

    @Override
    public List<Checkpoint> getAll() {
        return checkpointRepository.findAll();
    }

    @Override
    public List<Checkpoint> filter(Long id, String name, Long subjectId) {
        if (id == null && name.equals("") && subjectId == null){
            return getAll();
        }
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Checkpoint> criteriaQuery = criteriaBuilder.createQuery(Checkpoint.class);
        Root<Checkpoint> checkpoint = criteriaQuery.from(Checkpoint.class);

        Join<Checkpoint, Subject> subjectJoin = checkpoint.join("roles", JoinType.INNER);

        Predicate predicateForId = criteriaBuilder.equal(checkpoint.get("id"), id);
        Predicate predicateForName = criteriaBuilder.like(checkpoint.get("name"), name);
        Predicate predicateForSubject = criteriaBuilder.equal(subjectJoin.get("id").as(Long.class), subjectId);

        Predicate predicate = null;

        if(id!=null){
            predicate = criteriaBuilder.and(predicateForId);
        }
        if(subjectId != null){
            if (predicate != null)
                predicate = criteriaBuilder.and(predicate,predicateForSubject);
            else
                predicate = predicateForSubject;
        }
        if(name.equals("")){
            if (predicate != null)
                predicate = criteriaBuilder.and(predicate,predicateForName);
            else
                predicate = predicateForName;
        }

        criteriaQuery.select(checkpoint).where(predicate);
        List<Checkpoint> checkpointList = entityManager.createQuery(criteriaQuery).getResultList();

        Set<Checkpoint> checkpointSet = new HashSet<>(checkpointList);
        checkpointList.clear();
        checkpointList.addAll(checkpointSet);

        checkpointList.sort(Comparator.comparingLong(Checkpoint::getId));

        return checkpointList;
    }

    @Override
    public Checkpoint findById(Long id) {
        return checkpointRepository.findById(id).orElse(null);
    }

    @Override
    public Checkpoint findByName(String name) {
        return checkpointRepository.findByName(name);
    }

    @Override
    public Checkpoint findBySubjectId(Long subjectId) {
        return checkpointRepository.findBySubjectId(subjectId);
    }

    @Override
    public void save(Checkpoint checkpoint) {
        checkpointRepository.save(checkpoint);
    }

    @Override
    public Checkpoint deleteById(Long id) {
        Checkpoint checkpoint = checkpointRepository.findById(id).orElse(null);
        if (checkpoint!=null){
            checkpointRepository.deleteById(id);
            return checkpoint;
        }
        else return null;
    }
}
