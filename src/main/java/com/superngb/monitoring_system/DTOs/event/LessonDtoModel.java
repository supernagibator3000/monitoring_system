package com.superngb.monitoring_system.DTOs.event;

import com.superngb.monitoring_system.Entities.event.Lesson;
import com.superngb.monitoring_system.Entities.mark.Attendance;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LessonDtoModel implements Serializable {

    private Long id;
    private Long event;
    private List<Long> attendanceList;

    static public LessonDtoModel lessonMapper(Lesson lesson){
        List<Long> attendanceList = new ArrayList<>();

        for (Attendance attendance: lesson.getAttendanceList())
            attendanceList.add(attendance.getId());

        return new LessonDtoModel(lesson.getId(), lesson.getEvent().getId(), attendanceList);
    }

    static  public List<LessonDtoModel> listLessonsMapper(List<Lesson> lessons){
        List<LessonDtoModel> lessonDtoModels = new ArrayList<>();
        for (Lesson lesson: lessons)
            lessonDtoModels.add(lessonMapper(lesson));
        return lessonDtoModels;
    }
}
