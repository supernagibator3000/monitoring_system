package com.superngb.monitoring_system.DTOs;

import com.superngb.monitoring_system.Entities.Group;
import com.superngb.monitoring_system.Entities.Subject;
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
public class GroupDtoModel implements Serializable {

    private Long id;
    private String name;
    private List<Long> students;
    private List<Long> subjects;

    static public GroupDtoModel groupMapper(Group group) {
        List<Long> students = new ArrayList<>();
        List<Long> subjects = new ArrayList<>();

        for (Student student: group.getStudents())
            students.add(student.getId());

        for (Subject subject: group.getSubjects())
            subjects.add(subject.getId());

        return new GroupDtoModel(group.getId(), group.getName(), students, subjects);
    }

    static public List<GroupDtoModel> listGroupMapper(List<Group> groups) {
        List<GroupDtoModel> groupDtoModels = new ArrayList<>();
        for (Group group : groups)
            groupDtoModels.add(groupMapper(group));
        return groupDtoModels;
    }
}
