package com.superngb.monitoring_system.Entities.person;

import com.superngb.monitoring_system.Entities.Group;
import com.superngb.monitoring_system.Entities.Subject;
import com.superngb.monitoring_system.Entities.mark.Attendance;
import com.superngb.monitoring_system.Entities.mark.Score;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "students")
public class Student{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "personality_id", referencedColumnName = "id")
    private Personality personality;

    @Column(name = "student_card_id")
    private String studentCardId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "group_id", referencedColumnName = "id")
    private Group group;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name="students_subjects",
            joinColumns = {@JoinColumn(name="student_id", referencedColumnName="id")},
            inverseJoinColumns = {@JoinColumn(name="subject_id", referencedColumnName="id")}
    )
    private List<Subject> subjects;

    @OneToMany(mappedBy = "student")
    @JoinTable(name="students_scores",
            joinColumns = {@JoinColumn(name="student_id", referencedColumnName="id")},
            inverseJoinColumns = {@JoinColumn(name="score_id", referencedColumnName="id")}
    )
    private List<Score> scores;

    @OneToMany(mappedBy = "student")
    @JoinTable(name="students_attendance",
            joinColumns = {@JoinColumn(name="student_id", referencedColumnName="id")},
            inverseJoinColumns = {@JoinColumn(name="attendance_id", referencedColumnName="id")}
    )
    private List<Attendance> attendance;
}
