package com.superngb.monitoring_system.Database;

import com.superngb.monitoring_system.Entities.Subject;
import com.superngb.monitoring_system.Entities.event.Lesson;
import com.superngb.monitoring_system.Repositories.event.LessonRepository;
import com.superngb.monitoring_system.UseCases.AdminPanel.DataAccess.AdminPanelLessonDataAccess;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.*;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class LessonDataAccessImpl implements AdminPanelLessonDataAccess {

    private final LessonRepository lessonRepository;

    @PersistenceContext
    private EntityManager entityManager;

    public LessonDataAccessImpl(LessonRepository lessonRepository) {
        this.lessonRepository = lessonRepository;
    }

    @Override
    public List<Lesson> getAll() {
        return lessonRepository.findAll();
    }

    @Override
    public List<Lesson> filter(Long id, String name, Long subjectId) {
        if (id == null && name.equals("") && subjectId == null){
            return getAll();
        }
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Lesson> criteriaQuery = criteriaBuilder.createQuery(Lesson.class);
        Root<Lesson> lesson = criteriaQuery.from(Lesson.class);

        Join<Lesson, Subject> subjectJoin = lesson.join("subject", JoinType.INNER);

        Predicate predicateForId = criteriaBuilder.equal(lesson.get("id"), id);
        Predicate predicateForName = criteriaBuilder.like(lesson.get("name"), name);
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
        if(!name.equals("")){
            if (predicate != null)
                predicate = criteriaBuilder.and(predicate,predicateForName);
            else
                predicate = predicateForName;
        }

        criteriaQuery.select(lesson).where(predicate);
        List<Lesson> lessonList = entityManager.createQuery(criteriaQuery).getResultList();

        Set<Lesson> lessonSet = new HashSet<>(lessonList);
        lessonList.clear();
        lessonList.addAll(lessonSet);

        lessonList.sort(Comparator.comparingLong(Lesson::getId));

        return lessonList;
    }

    @Override
    public Lesson findById(Long id) {
        return lessonRepository.findById(id).orElse(null);
    }

    @Override
    public Lesson findByName(String name) {
        return lessonRepository.findByName(name);
    }

    @Override
    public Lesson findBySubjectId(Long subjectId) {
        return lessonRepository.findBySubjectId(subjectId);
    }

    @Override
    public void save(Lesson lesson) {
        lessonRepository.save(lesson);
    }

    @Override
    public Lesson deleteById(Long id) {
        Lesson lesson = lessonRepository.findById(id).orElse(null);
        if (lesson!=null){
            lessonRepository.deleteById(id);
            return lesson;
        }
        else return null;
    }
}
