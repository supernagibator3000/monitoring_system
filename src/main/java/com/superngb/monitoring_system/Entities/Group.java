package com.superngb.monitoring_system.Entities;

import com.superngb.monitoring_system.Entities.person.Student;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "groups")
public class Group {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinTable(name="groups_students",
            joinColumns = {@JoinColumn(name="group_id", referencedColumnName="id")},
            inverseJoinColumns = {@JoinColumn(name="student_id", referencedColumnName="id")}
    )
    private List<Student> students;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name="groups_subjects",
            joinColumns = {@JoinColumn(name="group_id", referencedColumnName="id")},
            inverseJoinColumns = {@JoinColumn(name="subject_id", referencedColumnName="id")}
    )
    private List<Subject> subjects;
}
