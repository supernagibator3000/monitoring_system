package com.superngb.monitoring_system.Database;

import com.superngb.monitoring_system.Entities.person.Personality;
import com.superngb.monitoring_system.Entities.person.Teacher;
import com.superngb.monitoring_system.Repositories.person.TeacherRepository;
import com.superngb.monitoring_system.UseCases.AdminPanel.DataAccess.AdminPanelTeacherDataAccess;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.*;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class TeacherDataAccessImpl implements AdminPanelTeacherDataAccess {

    private final TeacherRepository teacherRepository;

    @PersistenceContext
    private EntityManager entityManager;

    public TeacherDataAccessImpl(TeacherRepository teacherRepository) {
        this.teacherRepository = teacherRepository;
    }

    @Override
    public List<Teacher> getAll() {
        return teacherRepository.findAll();
    }

    @Override
    public List<Teacher> filter(Long id, Long personality) {
        if (id == null && personality == null){
            return getAll();
        }
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Teacher> criteriaQuery = criteriaBuilder.createQuery(Teacher.class);
        Root<Teacher> teacher = criteriaQuery.from(Teacher.class);

        Join<Teacher, Personality> personalityJoin = teacher.join("personality", JoinType.INNER);

        Predicate predicateForId = criteriaBuilder.equal(teacher.get("id"), id);
        Predicate predicateForPersonality = criteriaBuilder.equal(personalityJoin.get("id").as(Long.class), personality);

        Predicate predicate = null;

        if(id!=null){
            predicate = criteriaBuilder.and(predicateForId);
        }
        if(personality!=null){
            if (predicate != null)
                predicate = criteriaBuilder.and(predicate,predicateForPersonality);
            else
                predicate = predicateForPersonality;
        }

        criteriaQuery.select(teacher).where(predicate);
        List<Teacher> teacherList = entityManager.createQuery(criteriaQuery).getResultList();

        Set<Teacher> teacherSet = new HashSet<>(teacherList);
        teacherList.clear();
        teacherList.addAll(teacherSet);

        teacherList.sort(Comparator.comparingLong(Teacher::getId));

        return teacherList;
    }

    @Override
    public Teacher findById(Long id) {
        return teacherRepository.findById(id).orElse(null);
    }

    @Override
    public void save(Teacher teacher) {
        teacherRepository.save(teacher);
    }

    @Override
    public Teacher deleteById(Long id) {
        Teacher teacher = teacherRepository.findById(id).orElse(null);
        if (teacher!=null){
            teacherRepository.deleteById(id);
            return teacher;
        }
        else return null;
    }

    @Override
    public Teacher findByPersonalityId(Long personality) {
        return teacherRepository.findByPersonalityId(personality);
    }
}
