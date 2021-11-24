package com.superngb.monitoring_system.Database;

import com.superngb.monitoring_system.Entities.Subject;
import com.superngb.monitoring_system.Repositories.SubjectRepository;
import com.superngb.monitoring_system.UseCases.AdminPanel.DataAccess.AdminPanelSubjectDataAccess;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.*;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SubjectDataAccessImpl implements AdminPanelSubjectDataAccess {

    private SubjectRepository subjectRepository;

    @PersistenceContext
    private EntityManager entityManager;

    public SubjectDataAccessImpl(SubjectRepository subjectRepository) {
        this.subjectRepository = subjectRepository;
    }

    @Override
    public List<Subject> getAll() {
        return subjectRepository.findAll();
    }

    @Override
    public List<Subject> filter(Long id, String name) {
        if (id == null && name.equals("")){
            return getAll();
        }
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Subject> criteriaQuery = criteriaBuilder.createQuery(Subject.class);
        Root<Subject> subject = criteriaQuery.from(Subject.class);

        Predicate predicateForId = criteriaBuilder.equal(subject.get("id"), id);
        Predicate predicateForName = criteriaBuilder.like(subject.get("name"), name);

        Predicate predicate = null;

        if(id!=null){
            predicate = criteriaBuilder.and(predicateForId);
        }
        if(!name.equals("")){
            if (predicate != null)
                predicate = criteriaBuilder.and(predicate, predicateForName);
            else
                predicate = predicateForName;
        }

        criteriaQuery.select(subject).where(predicate);
        List<Subject> subjectList = entityManager.createQuery(criteriaQuery).getResultList();

        Set<Subject> subjectSet = new HashSet<>(subjectList);
        subjectList.clear();
        subjectList.addAll(subjectSet);

        subjectList.sort(Comparator.comparingLong(Subject::getId));

        return subjectList;
    }

    @Override
    public Subject findById(Long id) {
        return subjectRepository.findById(id).orElse(null);
    }

    @Override
    public Subject findByName(String name) {
        return subjectRepository.findByName(name);
    }

//    @Override
//    public List<Subject> findAllByGroups(List<Group> groups) {
//        return subjectRepository.findAllByGroups(groups);
//    }

    @Override
    public void save(Subject subject) {
        subjectRepository.save(subject);
    }

    @Override
    public Subject deleteById(Long id) {
        Subject subject = subjectRepository.findById(id).orElse(null);
        if (subject!=null){
            subjectRepository.deleteById(id);
            return subject;
        }
        else return null;
    }
}
