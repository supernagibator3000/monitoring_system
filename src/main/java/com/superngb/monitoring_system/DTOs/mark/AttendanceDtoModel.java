package com.superngb.monitoring_system.DTOs.mark;

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
public class AttendanceDtoModel implements Serializable {

    private Long id;
    private Long student;
    private String attendanceMark;
    private Long lesson;

    static public AttendanceDtoModel attendanceMapper(Attendance attendance){
        return new AttendanceDtoModel(attendance.getId(), attendance.getStudent().getId(), attendance.getAttendanceMark().getDescription(), attendance.getLesson().getId());
    }

    static public List<AttendanceDtoModel> listAttendanceMapper(List<Attendance> attendanceList){
        List<AttendanceDtoModel> attendanceDtoModels = new ArrayList<>();
        for (Attendance attendance: attendanceList)
            attendanceDtoModels.add(attendanceMapper(attendance));
        return attendanceDtoModels;
    }
}
