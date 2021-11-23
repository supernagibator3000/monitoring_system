package com.superngb.monitoring_system.Entities.mark;

import com.superngb.monitoring_system.Entities.event.Lesson;
import com.superngb.monitoring_system.Entities.person.Student;
import com.superngb.monitoring_system.Enums.AttendanceEnum;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "attendance")
public class Attendance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinTable(name="students_attendance",
            joinColumns = {@JoinColumn(name="attendance_id", referencedColumnName="id")},
            inverseJoinColumns = {@JoinColumn(name="student_id", referencedColumnName="id")}
    )
    private Student student;

    @Enumerated(EnumType.STRING)
    @Column(name = "mark")
    private AttendanceEnum attendanceMark;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinTable(name="lessons_attendance",
            joinColumns = {@JoinColumn(name="attendance_id", referencedColumnName="id")},
            inverseJoinColumns = {@JoinColumn(name="lesson_id", referencedColumnName="id")}
    )
    private Lesson lesson;
}
