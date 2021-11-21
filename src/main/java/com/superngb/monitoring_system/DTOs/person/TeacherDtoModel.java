package com.superngb.monitoring_system.DTOs.person;

import com.superngb.monitoring_system.Entities.Subject;
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
public class TeacherDtoModel implements Serializable {

    private Long id;
    private Long personality;
    private List<Long> subjects;

    static public TeacherDtoModel teacherMapper(Teacher teacher){
        List<Long> subjects = new ArrayList<>();

        for (Subject subject: teacher.getSubjects())
            subjects.add(subject.getId());

        return new TeacherDtoModel(teacher.getId(), teacher.getPersonality().getId(), subjects);
    }

    static public List<TeacherDtoModel> listTeachersMapper(List<Teacher> teachers){
        List<TeacherDtoModel> teacherDtoModels = new ArrayList<>();
        for (Teacher teacher: teachers)
            teacherDtoModels.add(teacherMapper(teacher));
        return teacherDtoModels;
    }
}
