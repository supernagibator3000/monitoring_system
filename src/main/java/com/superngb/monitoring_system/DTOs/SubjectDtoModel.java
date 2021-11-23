package com.superngb.monitoring_system.DTOs;

import com.superngb.monitoring_system.Entities.Group;
import com.superngb.monitoring_system.Entities.Subject;
import com.superngb.monitoring_system.Entities.event.Checkpoint;
import com.superngb.monitoring_system.Entities.event.Lesson;
import com.superngb.monitoring_system.Entities.person.Student;
import com.superngb.monitoring_system.Entities.person.Teacher;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SubjectDtoModel implements Serializable {

    private Long id;
    private String name;
    private List<Long> lessons;
    private List<Long> checkpoints;
    private List<Long> groups;
    private List<Long> students;
    private List<Long> teachers;

    static public SubjectDtoModel subjectMapper(Subject subject) {
        List<Long> lessons = new ArrayList<>();
        List<Long> checkpoints = new ArrayList<>();
        List<Long> groups = new ArrayList<>();
        List<Long> students = new ArrayList<>();
        List<Long> teachers = new ArrayList<>();

        for (Lesson lesson: subject.getLessons())
            lessons.add(lesson.getId());

        for (Checkpoint checkpoint: subject.getCheckpoints())
            checkpoints.add(checkpoint.getId());

        for (Group group: subject.getGroups())
            groups.add(group.getId());

        for (Student student: subject.getStudents())
            students.add(student.getId());

        for (Teacher teacher: subject.getTeachers())
            teachers.add(teacher.getId());

        return new SubjectDtoModel(subject.getId(), subject.getName(), lessons, checkpoints, groups, students, teachers);
    }

    static public List<SubjectDtoModel> listSubjectsMapper(List<Subject> subjects) {
        List<SubjectDtoModel> subjectDtoModels = new ArrayList<>();
        for (Subject subject : subjects)
            subjectDtoModels.add(subjectMapper(subject));
        return subjectDtoModels;
    }
}
