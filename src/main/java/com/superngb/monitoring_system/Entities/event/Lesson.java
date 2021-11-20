package com.superngb.monitoring_system.Entities.event;

import com.superngb.monitoring_system.Entities.mark.Attendance;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "lessons")
public class Lesson {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "event_id", referencedColumnName = "id")
    private Event event;

    @OneToMany(mappedBy = "lesson",fetch = FetchType.LAZY)
    @JoinTable(name="lessons_attendance",
            joinColumns = {@JoinColumn(name="lesson_id", referencedColumnName="id")},
            inverseJoinColumns = {@JoinColumn(name="attendance_id", referencedColumnName="id")}
    )
    private List<Attendance> attendanceList;
}
