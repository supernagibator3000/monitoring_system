package com.superngb.monitoring_system.Entities.person;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    @JoinTable(name="groups_students",
            joinColumns = {@JoinColumn(name="student_id", referencedColumnName="id")},
            inverseJoinColumns = {@JoinColumn(name="group_id", referencedColumnName="id")}
    )
    private Group group;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name="students_subjects",
            joinColumns = {@JoinColumn(name="student_id", referencedColumnName="id")},
            inverseJoinColumns = {@JoinColumn(name="subject_id", referencedColumnName="id")}
    )
    @JsonIgnore
    private List<Subject> subjects;

    @OneToMany(mappedBy = "student")
    @JsonIgnore
    private List<Score> scores;

    @OneToMany(mappedBy = "student")
    @JsonIgnore
    private List<Attendance> attendance;
}
