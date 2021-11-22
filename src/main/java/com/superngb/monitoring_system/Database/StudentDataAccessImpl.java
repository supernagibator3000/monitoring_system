package com.superngb.monitoring_system.Database;

import com.superngb.monitoring_system.Entities.Group;
import com.superngb.monitoring_system.Entities.person.Personality;
import com.superngb.monitoring_system.Entities.person.Student;
import com.superngb.monitoring_system.Entities.person.User;
import com.superngb.monitoring_system.Repositories.person.StudentRepository;
import com.superngb.monitoring_system.UseCases.AdminPanel.DataAccessInterfaces.AdminPanelStudentDataAccess;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.*;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class StudentDataAccessImpl implements AdminPanelStudentDataAccess {

    private final StudentRepository studentRepository;

    @PersistenceContext
    private EntityManager entityManager;

    public StudentDataAccessImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public List<Student> getAll() {
        return studentRepository.findAll();
    }

    @Override
    public List<Student> filter(Long id, String studentCardId, Long personality, Long group) {
        if (id == null && studentCardId.equals("") && personality == null && group == null){
            return getAll();
        }
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Student> criteriaQuery = criteriaBuilder.createQuery(Student.class);
        Root<Student> student = criteriaQuery.from(Student.class);

        Join<Student, Personality> personalityJoin = student.join("personality", JoinType.INNER);
        Join<Student, Group> groupJoin = student.join("group", JoinType.INNER);

        Predicate predicateForId = criteriaBuilder.equal(student.get("id"), id);
        Predicate predicateForPersonality = criteriaBuilder.equal(personalityJoin.get("id").as(Long.class), personality);
        Predicate predicateForGroup = criteriaBuilder.equal(groupJoin.get("id").as(Long.class), group);
        Predicate predicateForStudentCardId = criteriaBuilder.like(student.get("studentCardId"), studentCardId);

        Predicate predicate = null;

        if(id!=null){
            predicate = criteriaBuilder.and(predicateForId);
        }
        if(group!=null){
            if (predicate != null)
                predicate = criteriaBuilder.and(predicate,predicateForGroup);
            else
                predicate = predicateForGroup;
        }
        if(personality!=null){
            if (predicate != null)
                predicate = criteriaBuilder.and(predicate,predicateForPersonality);
            else
                predicate = predicateForPersonality;
        }
        if(!studentCardId.equals("")){
            if (predicate != null)
                predicate = criteriaBuilder.and(predicate,predicateForStudentCardId);
            else
                predicate = predicateForStudentCardId;
        }

        criteriaQuery.select(student).where(predicate);
        List<Student> studentList = entityManager.createQuery(criteriaQuery).getResultList();

        Set<Student> studentSet = new HashSet<>(studentList);
        studentList.clear();
        studentList.addAll(studentSet);

        studentList.sort(Comparator.comparingLong(Student::getId));

        return studentList;
    }

    @Override
    public Student findById(Long id) {
        return studentRepository.findById(id).orElse(null);
    }

    @Override
    public Student findByStudentCardId(String studentCardId) {
        return studentRepository.findByStudentCardId(studentCardId);
    }

    @Override
    public Student findByPersonalityId(Long personalityId) {
        return studentRepository.findByPersonalityId(personalityId);
    }

    @Override
    public Student findByGroupId(Long groupId) {
        return studentRepository.findByGroupId(groupId);
    }

    @Override
    public void save(Student student) {
        studentRepository.save(student);
    }

    @Override
    public Student deleteById(Long id) {
        Student student = studentRepository.findById(id).orElse(null);
        if (student!=null){
            studentRepository.deleteById(id);
            return student;
        }
        else return null;
    }
}
