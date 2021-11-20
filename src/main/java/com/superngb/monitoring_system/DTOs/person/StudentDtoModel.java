package com.superngb.monitoring_system.DTOs.person;

import com.superngb.monitoring_system.Entities.Subject;
import com.superngb.monitoring_system.Entities.mark.Attendance;
import com.superngb.monitoring_system.Entities.mark.Score;
import com.superngb.monitoring_system.Entities.person.Personality;
import com.superngb.monitoring_system.Entities.person.Student;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentDtoModel implements Serializable {

    private Long id;
    private Long personality;
    private String studentCardId;
    private Long group;
    private List<Long> subjects;
    private List<Long> scores;
    private List<Long> attendance;

    static public StudentDtoModel studentMapper(Student student){
        List<Long> subjects = new ArrayList<>();
        List<Long> scores = new ArrayList<>();
        List<Long> attendanceList = new ArrayList<>();

        for (Subject subject: student.getSubjects())
            subjects.add(subject.getId());

        for (Score score: student.getScores())
            scores.add(score.getId());

        for (Attendance attendance: student.getAttendance())
            attendanceList.add(attendance.getId());

        return new StudentDtoModel(student.getId(), student.getPersonality().getId(), student.getStudentCardId(), student.getGroup().getId(), subjects, scores, attendanceList);
    }

    static List<StudentDtoModel> listStudentMapper(List<Student> students){
        List<StudentDtoModel> studentDtoModels = new ArrayList<>();
        for (Student student: students)
            studentDtoModels.add(studentMapper(student));
        return studentDtoModels;
    }
}
