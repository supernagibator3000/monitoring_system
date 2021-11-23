package com.superngb.monitoring_system.Database;

import com.superngb.monitoring_system.Entities.event.Lesson;
import com.superngb.monitoring_system.Entities.mark.Attendance;
import com.superngb.monitoring_system.Entities.person.Student;
import com.superngb.monitoring_system.Repositories.mark.AttendanceRepository;
import com.superngb.monitoring_system.UseCases.AdminPanel.DataAccessInterfaces.AdminPanelAttendanceDataAccess;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.*;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class AttendanceDataAccessImpl implements AdminPanelAttendanceDataAccess {

    private AttendanceRepository attendanceRepository;

    @PersistenceContext
    private EntityManager entityManager;

    public AttendanceDataAccessImpl(AttendanceRepository attendanceRepository) {
        this.attendanceRepository = attendanceRepository;
    }

    @Override
    public List<Attendance> getAll() {
        return attendanceRepository.findAll();
    }

    @Override
    public List<Attendance> filter(Long id, Long studentId, Long lessonId) {
        if (id == null && studentId == null && lessonId == null){
            return getAll();
        }
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Attendance> criteriaQuery = criteriaBuilder.createQuery(Attendance.class);
        Root<Attendance> attendance = criteriaQuery.from(Attendance.class);

        Join<Attendance, Student> studentJoin = attendance.join("student", JoinType.INNER);
        Join<Attendance, Lesson> lessonJoin = attendance.join("lesson", JoinType.INNER);

        Predicate predicateForId = criteriaBuilder.equal(attendance.get("id"), id);
        Predicate predicateForStudent = criteriaBuilder.equal(studentJoin.get("id").as(Long.class), studentId);
        Predicate predicateForLesson = criteriaBuilder.equal(lessonJoin.get("id").as(Long.class), lessonId);

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
        if(lessonId != null){
            if (predicate != null)
                predicate = criteriaBuilder.and(predicate,predicateForLesson);
            else
                predicate = predicateForLesson;
        }

        criteriaQuery.select(attendance).where(predicate);
        List<Attendance> attendanceList = entityManager.createQuery(criteriaQuery).getResultList();

        Set<Attendance> attendanceSet = new HashSet<>(attendanceList);
        attendanceList.clear();
        attendanceList.addAll(attendanceSet);

        attendanceList.sort(Comparator.comparingLong(Attendance::getId));

        return attendanceList;
    }

    @Override
    public Attendance findById(Long id) {
        return attendanceRepository.findById(id).orElse(null);
    }

    @Override
    public Attendance findByLessonIdAndStudentId(Long lessonId, Long studentId) {
        return attendanceRepository.findByLessonIdAndStudentId(lessonId, studentId);
    }

    @Override
    public List<Attendance> findAllByStudentId(Long studentId) {
        return attendanceRepository.findAllByStudentId(studentId);
    }

    @Override
    public List<Attendance> findAllByLessonId(Long lessonId) {
        return attendanceRepository.findAllByLessonId(lessonId);
    }

    @Override
    public void save(Attendance attendance) {
        attendanceRepository.save(attendance);
    }

    @Override
    public Attendance deleteById(Long id) {
        Attendance attendance = attendanceRepository.findById(id).orElse(null);
        if (attendance!=null){
            attendanceRepository.deleteById(id);
            return attendance;
        }
        else return null;
    }
}
